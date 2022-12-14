- [Abstract](#abstract)
- [Materials](#materials)
- [Install](#install)
  - [Install collectd-influxdb-grafana on docker](#install-collectd-influxdb-grafana-on-docker)
  - [Install Collectd on Win64](#install-collectd-on-win64)
  - [Install Collectd on macOS](#install-collectd-on-macos)
- [Config for Collectd](#config-for-collectd)
- [Config for influxdb](#config-for-influxdb)
- [csv plugin](#csv-plugin)
- [target_replace plugin](#targetreplace-plugin)
- [Install to Systemd](#install-to-systemd)

----

# Abstract

logging agent 인 collectd 와 influxdb, grafana 를 연동하여 centralized logging system 을 구성해본다.

# Materials

* [How To Configure Collectd to Gather System Metrics for Graphite on Ubuntu 14.04 @ digitalocean](https://www.digitalocean.com/community/tutorials/how-to-configure-collectd-to-gather-system-metrics-for-graphite-on-ubuntu-14-04)
* [collectd @ github](https://github.com/collectd/collectd)
* [CollectD ubuntu 18.04](https://www.youtube.com/watch?v=l57-TGaYQak)

# Install

## Install collectd-influxdb-grafana on docker 

* [Try InfluxDB and Grafana by docker](https://blog.laputa.io/try-influxdb-and-grafana-by-docker-6b4d50c6a446)
  * [src](https://github.com/justlaputa/collectd-influxdb-grafana-docker)

----

```bash
$ cd ~/my/docker
$ git clone git@github.com:justlaputa/collectd-influxdb-grafana-docker.git
$ cd collectd-influxdb-grafana-docker.git
$ docker-compose up -d
# http://localhost:8083 influxdb admin page
# http://localhost:3000 grafana web page (login with admin/admin)
```

* Collectd doesn't work So I installed Collectd on Win64 And It worked. 

## Install Collectd on Win64

* [Collectd Win](https://ssc-serv.com/download.shtml) But Free Version can send a data just every 5 min.
* Should reset Server Config as `127.0.0.1`
  
## Install Collectd on macOS

```bash
$ brew install collectd
$ vim /usr/local/etc/collectd.conf
$ /usr/local/sbin/collectd -f -C /usr/local/etc/collectd.conf
```

# Config for Collectd

* [collectd로 system resource 모니터링](https://kbss27.github.io/2017/05/04/collectd/)

-----

* `/etc/collectd.conf`

```conf
--- Loadplugin section ---

LoadPlugin cpu
LoadPlugin df
LoadPlugin disk
LoadPlugin interface
LoadPlugin load
LoadPlugin memory
LoadPlugin network

--- Plugin configuration ---
<Plugin cpu>
  ReportByCpu true
  ReportByState true
  ValuesPercentage true
</Plugin>

...

<Plugin network>
#       # client setup:
        Server "127.0.0.1" "25826"
#       <Server "127.0.0.1" "25826">
#               SecurityLevel Encrypt
#               Username "user"
#               Password "secret"
#               Interface "eth0"
#               ResolveInterval 14400
#       </Server>
#       TimeToLive 128
#
#       # server setup:
#       Listen "127.0.0.1" "25826"
#       <Listen "239.192.74.66" "25826">
#               SecurityLevel Sign
#               AuthFile "/etc/collectd/passwd"
#               Interface "eth0"
#       </Listen>
        MaxPacketSize 1452
#
#       # proxy setup (client and server as above):
#       Forward true
#
#       # statistics about the network plugin itself
#       ReportStats false
#
#       # "garbage collection"
#       CacheFlush 1800
```

# Config for influxdb

* [collectd로 system resource 모니터링](https://kbss27.github.io/2017/05/04/collectd/)

-----

* ` /etc/influxdb/influxdb.conf`

```conf
###
### [collectd]
###
### Controls the listener for collectd data.
###

[collectd]
  enabled = true
  bind-address = ":25826"
  database = "collectd"
  typesdb = "/usr/share/collectd/types.db"

  # These next lines control how batching works. You should have this enabled
  # otherwise you could get dropped metrics or poor performance. Batching
  # will buffer points in memory if you have many coming in.

  # batch-size = 1000 # will flush if this many points get buffered
  # batch-pending = 5 # number of batches that may be pending in memory
  # batch-timeout = "1s" # will flush at least this often even if we haven't hit buffer limit
  # read-buffer = 0 # UDP Read buffer size, 0 means OS default. UDP listener will fail if set above OS max.
```

* check the data

  * browse `http://localhost:8083`
  * `SHOW DATABASES`
  * `show measurements`
    ```
    cpu_value
    df_value
    disk_read
    disk_value
    disk_write
    interface_rx
    interface_tx
    memory_value
    processes_processes
    processes_threads
    ```
  * `select * from cpu_value`
  * `select * from memory_value`
  * `select * from df_value`

# csv plugin

Can save metrics to a csv file. Please notice that this might cause disk full situcations.

* `/etc/collectd/collectd.conf.d/csv.conf`

```conf
LoadPlugin csv

<Plugin "csv">
  DataDir "/var/lib/collectd/csv"
  StoreRates true
</Plugin>
```

# target_replace plugin

* [Match:RegEx](https://collectd.org/wiki/index.php/Match:RegEx)
* [Target:Replace](https://collectd.org/wiki/index.php/Target:Replace)

----

Can replace metrics.

* `/etc/collectd/collectd.conf.d/target_replace.conf`
  * You should use `?...$` in Host.

```conf
LoadPlugin "regex"
LoadPlugin "target_replace"

<Chain "PreCache">
  <Rule "replace_host_foo">
    <Match "regex">
      Host "?www.foo.com$"
    </Match>
    <Target "replace">
      Host "\\<www.foo.com\\>" "www.baz.com"
    </Target>
  </Rule>
  <Rule "replace_host_bar">
    <Match "regex">
      Host "?www.bar.com$"
    </Match>
    <Target "replace">
      Host "\\<www.bar.com\\>" "www.baz.com"
    </Target>
  </Rule>
</Chain>

```

# Install to Systemd

* [systemd](/systemd/README.md)

----

* `vim /etc/collectd.conf`

```
...
Hostname    "platform.github.i-0c3a809def28db035.github_test"
...
<Include "/etc/collectd/collectd.conf.d">
        Filter "*.conf"
</Include>
```

* `vim /etc/collectd.d/`

* `vim /etc/collectd.d/*`

* `vim /etc/systemd/system/collectdbro.service`

```
[Unit]
Description=Statistics collection and monitoring daemon
After=local-fs.target network.target
Requires=local-fs.target network.target
ConditionPathExists=/etc/collectd/collectd.conf
Documentation=man:collectd(1)
Documentation=man:collectd.conf(5)
Documentation=https://collectd.org

[Service]
Type=notify
NotifyAccess=main
EnvironmentFile=-/etc/default/collectd
ExecStartPre=/usr/sbin/collectd -t
ExecStart=/usr/sbin/collectd
Restart=always
RestartSec=10

[Install]
WantedBy=multi-user.target
```

* register collectdbro.service

```bash
$ sudo systemctl enable collectdbro.service
$ sudo systemctl daemon-reload
$ sudo systemctl restart collectdbro
$ sudo systemctl status collectdbro
$ sudo systemctl edit collectdbro --full
```
