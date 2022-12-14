- [Abstract](#abstract)
- [Materials](#materials)
- [Architecture](#architecture)
  - [Overview](#overview)
  - [Kubernetes Components](#kubernetes-components)
    - [Master Node](#master-node)
    - [Worker Node](#worker-node)
    - [Addons](#addons)
- [Authorization](#authorization)
- [Install](#install)
  - [AWS EKS](#aws-eks)
  - [Google GCP](#google-gcp)
  - [Microsoft AZURE](#microsoft-azure)
  - [Install on Win64](#install-on-win64)
  - [Install on macOS](#install-on-macos)
- [AWS EKS Basic](#aws-eks-basic)
- [Basic](#basic)
  - [Useful Commands](#useful-commands)
  - [Launch Single Pod](#launch-single-pod)
  - [Lunach Pods with livnessprobe, readynessprobe](#lunach-pods-with-livnessprobe-readynessprobe)
    - [key commands](#key-commands)
    - [Launch Simple Pod](#launch-simple-pod)
    - [Launch Simple Pod with LivenessProbe](#launch-simple-pod-with-livenessprobe)
    - [Launch Simple Pod with ReadinessProbe](#launch-simple-pod-with-readinessprobe)
    - [Launch Simple Pod with HealthCheck](#launch-simple-pod-with-healthcheck)
    - [Launch Simple Pod with Multi Containers](#launch-simple-pod-with-multi-containers)
    - [Delete All resources](#delete-all-resources)
  - [Launch Replicaset](#launch-replicaset)
    - [Launch Simple Replicaset](#launch-simple-replicaset)
    - [Launch ReplicaSet Scale out](#launch-replicaset-scale-out)
  - [Launch Deployment](#launch-deployment)
    - [Launch Simple Deployment](#launch-simple-deployment)
    - [Launch Deployment with RollingUpdate](#launch-deployment-with-rollingupdate)
  - [Launch Service](#launch-service)
    - [Launch Simple Service](#launch-simple-service)
    - [Launch Service with NodePort](#launch-service-with-nodeport)
  - [Launch LoadBalancer](#launch-loadbalancer)
    - [Launch Simple LoadBalancer](#launch-simple-loadbalancer)
    - [????](#)
    - [???](#-1)
    - [???](#-2)
  - [Launch Ingress](#launch-ingress)
    - [Launch Simple Ingress](#launch-simple-ingress)
    - [????](#-3)
  - [Launch Horizontal Pod Autoscaler](#launch-horizontal-pod-autoscaler)
    - [Launch Simple Horizontal Pod Autoscaler](#launch-simple-horizontal-pod-autoscaler)
  - [Launch Kubernetes Dashboard](#launch-kubernetes-dashboard)
- [Dive Deep](#dive-deep)
  - [controller](#controller)
  - [API server](#api-server)
  - [Monitoring](#monitoring)

----

# Abstract

Kubernetes ??? ???????????? Container ?????? ???????????? ??? ?????? ????????????. 

# Materials

* [???????????????! ??????/???????????????](http://www.yes24.com/Product/Goods/84927385)
  * [src](https://github.com/alicek106/start-docker-kubernetes)
  * ????????? ??? ??????
* [A few things I've learned about Kubernetes](https://jvns.ca/blog/2017/06/04/learning-about-kubernetes/)
  * [What even is a kubelet?](http://kamalmarhubi.com/blog/2015/08/27/what-even-is-a-kubelet/#n2)
  * [Kubernetes from the ground up: the API server](http://kamalmarhubi.com/blog/2015/09/06/kubernetes-from-the-ground-up-the-api-server/)
  * [Kubernetes from the ground up: the scheduler](http://kamalmarhubi.com/blog/2015/11/17/kubernetes-from-the-ground-up-the-scheduler/)
  * [Authenticating](https://kubernetes.io/docs/reference/access-authn-authz/authentication/)
  * [A container networking overview](https://jvns.ca/blog/2016/12/22/container-networking/)
* [Kubernetes in Action](http://acornpub.co.kr/book/k8s-in-action)
  * [src](https://github.com/luksa/kubernetes-in-action?files=1)
* [Comprehensive Guide to EKS Worker Nodes ](https://blog.gruntwork.io/comprehensive-guide-to-eks-worker-nodes-94e241092cbe)
  * About kernetes worker node management
* [CNCF @ youtube](https://www.youtube.com/channel/UCvqbFHwN-nwalWPjPUKpvTA)
  * Cloud Native Computing Foundation
* [Kubernetes Blog](https://kubernetes.io/blog/)
* [EKS workshop](https://eksworkshop.com/010_introduction/basics/concepts_nodes/)
  * This explains about K8s
* [[??????ON?????????] ??????????????? ???????????? @ youtube](https://www.youtube.com/watch?v=xZ3tcFvbUGc&list=PLinIyjMcdO2SRxI4VmoU6gwUZr1XGMCyB&index=2)
  * ????????? ????????? ????????? ??????
  * [Workshop ?????? ?????? ???????????? @ github](https://github.com/subicura/workshop-init)
  * [kubernetes ?????? ????????? @ github](https://github.com/subicura/workshop-k8s-basic)
* [AWS Kubernetes ????????? ????????? ???????????? - ????????? ???????????? ????????????(AWS), ????????? ???????????? ????????????(AWS)](https://www.youtube.com/watch?v=iAP_pTrm4Eo)
  * [slide](https://www.slideshare.net/awskorea/aws-kubernetes-aws-aws-devday2018)
* [Kubernetes Deconstructed: Understanding Kubernetes by Breaking It Down - Carson Anderson, DOMO](https://www.youtube.com/watch?v=90kZRyPcRZw&fbclid=IwAR0InYUnMQD-t-o8JhS5U5KRRaJvSQQc1fBDeBCb8cv6eRk62vsG2Si_Ijo)
  * [slide](http://kube-decon.carson-anderson.com/Layers/3-Network.sozi.html#frame4156)
* [Comprehensive Guide to EKS Worker Nodes](https://blog.gruntwork.io/comprehensive-guide-to-eks-worker-nodes-94e241092cbe)
  * Self Managed Worker Nodes, Managed Node groups, Serverless Worker Nodes
* [A Practical Step-by-Step Guide to Understanding Kubernetes](https://medium.com/better-programming/a-practical-step-by-step-guide-to-understanding-kubernetes-d8be7f82e533)
  * Launch Django, PostgreSQL, Redis on Kubernetes.

# Architecture

## Overview

Kubernetes cluster ??? Master-node, Workder-node ??? ?????? ??? ?????? ????????? Node ??? ?????????. 

* A Master-node type, which makes up the Control Plane, acts as the ???brains??? of the cluster.
* A Worker-node type, which makes up the Data Plane, runs the actual container images (via pods).

Kubernetes cluster ??? current state ??? object ??? ????????????. Kubernetes ??? current state ??? object ?????? ?????? ??????????????? desired state ??? object ??? ???????????? ?????? ?????? current state object ?????? desired state state ?????? ????????????. 

Kubernetes object ??? Pod, DaemonSet, Deployment, ReplicaSet, Job, Service, Label ?????? ??????.

* Pod
  * A thin wrapper around one or more containers
* DaemonSet
  * Implements a single instance of a pod on a worker node
* Deployment
  * Details how to roll out (or roll back) across versions of your application
* ReplicaSet
  * Ensures a defined number of pods are always running
* Job
  * Ensures a pod properly runs to completion
* Service
  * Maps a fixed IP address to a logical group of pods
* Label
  * Key/Value pairs used for association and filtering

Kubernetes ??? Control Plane ??? Data Plane ?????? ????????????.

![](img/KubernetesArchitecturalOverview.png)

Ctonrol Plane ??? Scheduler, Controller Manager, API Server, etcd ????????? ????????????.

* One or More API Servers: Entry point for REST / kubectl
* etcd: Distributed key/value store
* Controller-manager: Always evaluating current vs desired state
* Scheduler: Schedules pods to worker nodes

Data Plane ??? kube-proxy, kubelet ????????? ????????????.

* Made up of worker nodes
* kubelet: Acts as a conduit between the API server and the node
* kube-proxy: Manages IP translation and routing

Controller ??? ????????? **ReplicaSet, Deployment, StatefulSet, DaemonSet, Job** ?????? ??????. Kubernetes ??? yaml ????????? ???????????? ????????????.

```yaml
apiVersion : v1
Kind : Pod
```

Kind ??? ?????? ?????? ??????????????? ?????? Object ?????? controller ??? ?????? ???????????? ??? ??? ??????.

Kubernetes Cluster ??? Master ??? Node ????????? ????????? ??????. 

Master ??? **etcd, kube-apiserver, kube-scheduler, kube-controller-manager, kubelet, kube-proxy, docker** ?????? ????????????. Master ?????? 1 ?????? ?????? ????????? ??????????????? ??? ????????? ?????? ??????????????? ???????????? ????????????. Master ??? ??????????????? High Availibility ??? ?????? 3 ??? ????????????. ?????? 1 ?????? ??????????????? ????????? 2 ?????? ???????????????.

Node ??? ????????? ?????????(minion) ????????? ?????????. Node ??? **kubelet, kube-proxy, docker** ?????? ????????????. ???????????? ?????????????????? Node ?????? ????????????.

![](https://upload.wikimedia.org/wikipedia/commons/b/be/Kubernetes.png)

?????? ????????? Kubernetes System Diagram ??????. Master ??? ???????????? Node ?????? ????????????. Operator ??? ????????? Master ??? API Server ??? ????????????. Node ??? ?????? ??????????????????.

## Kubernetes Components

### Master Node

* ETCD
  * key-value ?????????
* kube-apiserver
  * kubernetes cluster api ??? ????????? ??? ?????? ????????? gateway ??????. ???????????? ????????? ???????????? ???????????? ?????? ????????? ????????????.
* kube-scheduler
  * ?????? cluster ????????? ??????????????? ????????? Node ??? ?????? ???????????? ????????? pod ??? ????????????. Pod ??? ?????? ????????? ??? ???????????? ????????? ??????????????? kube-scheduler ??? ??? ????????? ?????? Node ??? ????????????. ?????? ?????? ????????? ???????????? ????????????, affinity, anti-affinity, ?????? ???????????? ????????? ?????? ????????????.
* kube-controller-manager
  * kubernetes ??? controller ?????? Pod ?????? ????????????. kube-controller-manager ??? controller ?????? ????????????.
* cloud-controller-manager
  * ??? ?????? cloud ??? ????????? ??? ????????????. 
  * Node Controller, Route Controller, Service Controller, Volume Controler ?????? ???????????? ??????.

### Worker Node

* kubelet
  * ?????? Worker Node ?????? ???????????? agent ??????. Pod ??? Container ??? ???????????? ?????? ????????????. PodSpecs ?????? ????????? ????????? ??? ????????? ?????? Container ??? ???????????? Container ??? ??????????????? ???????????? ????????? ?????? ????????? ??????.
* kube-proxy 
  * kubernetes ??? cluster ?????? virtual network ??? ???????????? ????????????. kube-proxy ??? virtual network ??? ????????? ??? ??????????????? process ??????. host ??? network ????????? ??????????????? connection forwarding ??? ??????.
* container runtime
  * container ??? ????????????. ?????? ?????? ????????? container runtime ??? docker ??????. container ??? ?????? ????????? ???????????? [OCI(Open Container Initiative)](https://www.opencontainers.org/) ??? runtime-spec ??? ???????????? container runtime ????????? kubernetes ?????? ????????? ??? ??????.
* cAdvisor (container advisor)
  * ????????? ??????, ?????? ????????? ??????

### Addons

cluster ????????? ????????? ???????????? ?????? ???????????? Pod ?????????. ?????? Deployment Controller, Replication Controller ??? ?????? ????????????. Addon ??? ???????????? namespace ??? kub-system ??????.

* Networking Addon
* DNS Addon
* Dashboard Addon
* Container resource monitoring
* cluster logging

# Authorization

* [??????????????? ????????????(Authorization)](https://arisu1000.tistory.com/27848)

# Install

## AWS EKS

* [Kubernetes On AWS | AWS Kubernetes Tutorial | AWS EKS Tutorial | AWS Training | Edureka](https://www.youtube.com/watch?v=6H5sXQoJiso)
  * [Getting Started with the AWS Management Console](https://docs.aws.amazon.com/eks/latest/userguide/getting-started-console.html#w243aac11b9b7c11b7b1)
* [Amazon EKS ????????????](https://aws.amazon.com/ko/eks/getting-started/)
* [Amazon EKS Starter: Docker on AWS EKS with Kubernetes @ udemy](https://www.udemy.com/course/amazon-eks-starter-kubernetes-on-aws/)
* [AWS ?????? Kubernetes ???????????? - ????????? ???????????? ????????????(AWS)](https://www.youtube.com/watch?v=lGw2y-GLBbs)
* [Getting Started with Amazon EKS](https://docs.aws.amazon.com/en_en/eks/latest/userguide/getting-started.html)

----

AWS eks ??? ??????????????? ????????? ?????? ????????? Kubernetes cluster ??? ????????? ??? ??? ??????.

* Create EKS cluster
* Provision worker nodes
* Launch add-ons
* Launch workloads

AWS eks ??? ???????????? Kubernetes cluster ??? ????????? ????????? ?????? ?????? ????????????.

* Create HA Control Plane
* IAM integration
* Certificate Management
* Setup LB
  
????????? AWS EKS ??? Architecture ??????.

![](img/eks-architecture.svg)

AWS EKS cluster ??? ??????????????? ?????? ????????? ?????? kubectl ??? ?????? API endpoint ??? ????????? ??? ??????.

![](img/eks-high-level.svg)

* create IAM role "`eks-role`" 
  * with policies "`AmazonEKSClusterPolicy, AmazonEKSServicePolicy`"
* create Network (VPC, subnets, security groups) "`eks-net`" with CloudFormation
  * with the template body `https://amazon-eks.s3-us-west-2.amazonaws.com/cloudformation/2019-11-15/amazon-eks-vpc-sample.yaml`
* create EKS cluster "`nginx-cluster`"
* install kubectl

  ```bash
  $ kubectl version --short --client
  ```

* install aws cli

  ```bash
  $ aws --version
  ```

* install aws-iam-authenticator

   ```bash
   $ brew install aws-iam-authenticator
   $ aws-iam-authenticator --help
   ```

* Create a kubeconfig File

  ```bash
  $ aws --region ap-northeast-2 eks update-kubeconfig --name nginx-cluster
  Added new context arn:aws:eks:ap-northeast-2:612149981322:cluster/nginx-cluster to /Users/davidsun/.kube/config
  ```

* create worker nodes "`nginx-cluster-worker-nodes`" with CloudFormation
  * with the template body `https://amazon-eks.s3-us-west-2.amazonaws.com/cloudformation/2019-11-15/amazon-eks-nodegroup-role.yaml`

* create k8s ConfigMap and connect `nginx-cluster-worker-nodes` to `nginx-cluster`
  
  * `aws-iam-authenticator.yaml`

    ```yaml
    apiVersion: v1
    kind: ConfigMap
    metadata:
      name: aws-auth
      namespace: kube-system
    data:
      mapRoles:
        - rolearn: <NodeInstanceRole of CloudFormation nginx-cluster-worker-nodes>
          username: system:node:{{EC2PrivateDNSName}}
          groups:
            - system:bootstrappers
            - system:nodes
    ```

  * apply

    ```bash
    $ kubectl apply -f aws-iam-authenticator.yaml
    $ kubectl get nodes
    ```

* create k8s Deployment, Service 

  * `nginx-deploy.yaml`

    ```yaml
    apiVersion: apps/v1
    kind: Deployment
    metadata:
      name: nginx
    spec:
      selector:
        matchlabels:
          run: nginx
      replicas: 2
      template:
        metadata:
          labels:
            run: nginx
        spec:
          containers:
          - name: nginx
            image: nginx:1.7.9
            ports:
            - containerPort: 80 
    ```

  * `nginx-service.yaml`

    ```yaml
    apiVersion: v1
    kind: Service
    metadata:
      name: nginx
      labels:
        run: nginx
    spec:
      ports:
      - port: 80
        protocol: TCP
      selector:
        run: nginx
      type: LoadBalancer
    ```

  * apply

    ```bash
    $ kubectl create -f nginx-deploy.yaml
    $ kubectl create -f nginx-service.yaml
    $ kubectl get services -o wide
    # copy LoadBalancer Ingress
    $ kubectl describe svc nginx
    ```

* open browser copied url

## Google GCP

Updating...

## Microsoft AZURE

Updating...

## Install on Win64

* Install docker, enable kubernetes. That's all.
* If you meet the issue like following, set env `%KUBECONFIG%` as `c:\Users\iamslash\.kube\config`

```bash
> kubectl config get-contexts
CURRENT   NAME      CLUSTER   AUTHINFO   NAMESPACE
> kubectl version
...
Unable to connect to the server: dial tcp [::1]:8080: connectex: No connection could be made because the target machine actively refused it.
```

## Install on macOS

* [??????(Docker), ???????????????(Kubernetes) ?????? ?????? ??????????????? ???????????? ????????? ????????? @ 44bits](https://www.44bits.io/ko/post/news--release-docker-desktop-with-kubernetes-to-stable-channel)

* Install docker, enable kubernetes. That's all.

# AWS EKS Basic

* [EKS workshop beginner](https://eksworkshop.com/beginner/)
  * AWS EKS handsson
  * [src](https://github.com/aws-samples/eks-workshop)

# Basic

## Useful Commands

* [workshop-k8s-basic/guide/guide-03/task-01.md](https://github.com/subicura/workshop-k8s-basic/blob/master/guide/guide-03/task-01.md)
  * [[??????ON?????????] ??????????????? ???????????? 6??? - Kubernetes(???????????????) ?????? 1 | T????????????](https://www.youtube.com/watch?v=G0-VoHbunks&list=PLinIyjMcdO2SRxI4VmoU6gwUZr1XGMCyB&index=6)
* [kubectl ?????? ??????](https://kubernetes.io/ko/docs/reference/kubectl/cheatsheet/)

----

* config

```bash
# show current cluster
$ kubectl config view
```

* api-resources

```bash
# show all objects including node, pod, replicaset, deployemnt,
# service, loadbalancer, ingress, volume, configmap, secret,
# namespace
$ kubectl api-resources
$ kubectl api-versions
$ kubectl explain deploy
$ kubectl explain deploy --api-version apps/v1
kubectl api-resources | awk '{print $3}' | sort | uniq
APIGROUP
Binding
PodTemplate
Secret
apiextensions.k8s.io
apps
autoscaling
awslb.coupang.com
batch
certificates.k8s.io
events.k8s.io
extensions
false
networking.k8s.io
policy
scheduling.k8s.io
storage.k8s.io
true
workload.coupang.com
```

* inspecting clusters

```bash
# check curretn cluster
$ kubectl config view
# check namespaces
$ kubectl get namespaces
# get all resources
$ kubectl get all --namespace kube-system 
$ kubectl get nodes --namespace kube-system
$ kubectl get pods --namespace kube-system
$ kubectl --namespace kube-system get pods -o wide # wide format
$ kubectl --namespace <ns> get pod/my-pod -o yaml # show pod's yaml
$ kubectl --namespace <ns> get pod/my-pod -o json # show pod's json
# get nodes with all namespaces
$ kubectl get nodes -A
# describe nodes
$ kubectl --namespace <ns> describe nodes <pn> 
# dscribe pods
$ kubectl --namespace <ns> describe pods <pn>
# show manifest of resource
$ kubectl explain pods,svc

# sort by name
$ kubectl --namespace <ns> get services --sort-by=.metadata.name
# sort by restartCount
$ kubectl --namespace <ns> get pods --sort-by='.status.containerStatuses[0].restartCount'

# logs
$ kubectl --namespace <ns> logs <pod-name>
$ kubectl --namespace <ns> logs <pod-name> -c <container-name>
$ kubectl --namespace <ns> logs -f <pod-name>
$ kubectl --namespace <ns> logs -f <pod-name> -c <container-name>

# run
$ kubectl --namespace <ns> run -it busybox --image=busybox -- sh
# attach
$ kubectl --namespace <ns> attach <pod-name> -i
# forward port
$ kubectl --namespace <ns> port-forward <pod-name> 5000:6000
# exec
$ kubectl --namespace <ns> exec <pod-name> -- ls /
$ kubectl --namespace <ns> exec <pod-name> -c <container-name> -- ls /
$ kubectl top pod <pod-name> --containers
```

* get

```bash
# show recent pod, replicaset, deployment, service not all
$ kubectl get all

# show nodes
$ kubectl get no
$ kubectl get node
$ kubectl get nodes

# change result format
$ kubectl get nodes -o wide
$ kubectl get nodes -o yaml
$ kubectl get nodes -o json
$ kubectl get nodes -o json |
      jq ".items[].metadata.name"
$ kubectl get nodes -o json |
      jq ".items[] | {name:.metadata.name} + .status.capacity"

# show pods with the namespace
$ k get pods --all-namespace
$ k get pods --namespace kube-system
```

* describe

```bash
# kubectl describe type/name
# kubectl describe type name
kubectl describe node <node name>
kubectl describe node/<node name>
```

* etc

```bash
kubectl exec -it <POD_NAME>
kubectl logs -f <POD_NAME|TYPE/NAME>

kubectl apply -f <FILENAME>
kubectl delete -f <FILENAME>
```

## Launch Single Pod

```bash
# Create my-nginx-* pod and my-nginx deployment
> kubectl run my-nginx --image nginx --port=80
# Show running pods
> kubectl get pods
# Show deployments. Deployment is a specification for deploying pods.
> kubectl get dployments
# Scale out my-nginx deployment.
> kubectl scale deploy my-nginx --replicas=2
# Create a service to expose my-nginx pods. These are kinds of services. ClusterIP, NodePort, LoadBalancer, ExteralName
> kubectl expose deployment my-nginx --type=NodePort
# show services
> kubectl get services
# show details of my-nginx service
> kubectl describe service my-nginx
# Delete my-nginx deployment including pods.
> kubectl delete deployment my-nginx
# Delete my-nginx service
> kubectl delete service my-nginx
```

## Lunach Pods with livnessprobe, readynessprobe

* [workshop-k8s-basic/guide/guide-03/task-02.md](https://github.com/subicura/workshop-k8s-basic/blob/master/guide/guide-03/task-02.md)
  * [[??????ON?????????] ??????????????? ???????????? 6??? - Kubernetes(???????????????) ?????? 1 | T????????????](https://www.youtube.com/watch?v=G0-VoHbunks&list=PLinIyjMcdO2SRxI4VmoU6gwUZr1XGMCyB&index=6)

----

### key commands

```bash
$ kubectl run whoami --image subicura/whoami:1 
$ kubectl get po
$ kubectl get pod
$ kubectl get pods
$ kubectl get pods -o wide
$ kubectl get pods -o yaml
$ kubectl get pods -o json
$ kubectl logs whoami-<xxxx>
$ kubectl logs -f whoami-<xxxx>
$ kubectl exec -it whoami-<xxxx> sh
$ kubectl describe pods whoami-<xxxx>
$ kubectl delete pods whoami-<xxxx>
$ kubectl get pods
$ kubectl get all
$ kubectl delete deployment/whoami
```

### Launch Simple Pod

* whoami-pod.yml
   
```yml
apiVersion: v1
kind: Pod
metadata:
  name: whoami
  labels:
    type: app
spec:
  containers:
  - name: app
    image: subicura/whoami:1
```
* launch

```bash
$ kubectl apply -f whoami-pod.yml
```

### Launch Simple Pod with LivenessProbe

* whoami-pod-lp.yml

```yml
apiVersion: v1
kind: Pod
metadata:
  name: whoami-lp
  labels:
    type: app
spec:
  containers:
  - name: app
    image: subicura/whoami:1
    livenessProbe:
      httpGet:
        path: /not/exist
        port: 8080
      initialDelaySeconds: 5
      timeoutSeconds: 2 # Default 1
      periodSeconds: 5 # Defaults 10
      failureThreshold: 1 # Defaults 3
```

* launch

```bash
$ kubectl apply -f whoami-pod-lp.yml
```

### Launch Simple Pod with ReadinessProbe

* whoami-pod-rp.yml

```yml
apiVersion: v1
kind: Pod
metadata:
  name: whoami-rp
  labels:
    type: app
spec:
  containers:
  - name: app
    image: subicura/whoami:1
    readinessProbe:
      httpGet:
        path: /not/exist
        port: 8080
      initialDelaySeconds: 5
      timeoutSeconds: 2 # Default 1
      periodSeconds: 5 # Defaults 10
      failureThreshold: 1 # Defaults 3
```

* launch

```bash
$ kubectl apply -f whoami-pod-rp.yml
```

### Launch Simple Pod with HealthCheck

* whoami-pod-health.yml

```yml
apiVersion: v1
kind: Pod
metadata:
  name: whoami-health
  labels:
    type: app
spec:
  containers:
  - name: app
    image: subicura/whoami:1
    livenessProbe:
      httpGet:
        path: /
        port: 4567
    readinessProbe:
      httpGet:
        path: /
        port: 4567
```

* launch

```bash
$ kubectl apply -f whoami-pod-health.yml
```

### Launch Simple Pod with Multi Containers

* whoami-pod-redis.yml

```yml
apiVersion: v1
kind: Pod
metadata:
  name: whoami-redis
  labels:
    type: stack
spec:
  containers:
  - name: app
    image: subicura/whoami-redis:1
    env:
    - name: REDIS_HOST
      value: "localhost"
  - name: db
    image: redis
```

* launch

```bash
$ kubectl apply -f whoami-pod-redis.yml
$ kubectl get all
$ kubectl logs whoami-redis
$ kubectl logs whoami-redis app
$ kubectl logs whoami-redis db
$ kubectl exec -it whoami-redis
$ kubectl exec -it whoami-redis -c db sh
$ kubectl exec -it whoami-redis -c app sh
  apk add curl busybox-extras # install telnet
  curl localhost:4567
  telnet localhost 6379
    dbsize
    KEYS *
    GET count
    quit
$ kubectl get pod/whoami-redis
$ kubectl get pod/whoami-redis -o yaml
$ kubectl get pod/whoami-redis -o jsonpath="{.spec.containers[0].name}"
$ kubectl get pod/whoami-redis -o jsonpath="{.spec.containers[*].name}"
$ kubectl describe pod/whoami-redis
```

### Delete All resources

```bash
$ kubectl delete -f ./
```

## Launch Replicaset

* [workshop-k8s-basic/guide/guide-03/task-03.md](https://github.com/subicura/workshop-k8s-basic/blob/master/guide/guide-03/task-03.md)
  * [[??????ON?????????] ??????????????? ???????????? 6??? - Kubernetes(???????????????) ?????? 1 | T????????????](https://www.youtube.com/watch?v=G0-VoHbunks&list=PLinIyjMcdO2SRxI4VmoU6gwUZr1XGMCyB&index=6)

----

### Launch Simple Replicaset

We use Deployment more than Replicaset. ReplicaSet is used in Deployment.

* whoami-rs.yml
  * ReplicaSet is still beta.
  * If there is no pod such as selector, Launch pod with template. 

```yml
apiVersion: apps/v1beta2
kind: ReplicaSet
metadata:
  name: whoami-rs
spec:
  replicas: 1
  selector:
    matchLabels:
      type: app
      service: whoami
  template:
    metadata:
      labels:
        type: app
        service: whoami
    spec:
      containers:
      - name: whoami
        image: subicura/whoami:1
        livenessProbe:
          httpGet:
            path: /
            port: 4567
```

* launch

```bash
$ kubectl apply -f whoami-rs.yml
$ kubectl get pods --show-labels
# If remove service from label, ReplicatSet launch another pod
$ kubectl label pod/whoami-rs-<xxxx> service-
# set label
$ kubectl label pod/whoami-rs-<xxxx> service=whoami
# modify replicas as 3 and apply again
#   It is same with kubectl scale --replicas=3 -f whoami-rs.yml
$ kubectl apply -f whoami-rs.yml
```

### Launch ReplicaSet Scale out

* whoami-rs-scaled.yml

```yml
apiVersion: apps/v1beta2
kind: ReplicaSet
metadata:
  name: whoami-rs
spec:
  replicas: 4
  selector:
    matchLabels:
      type: app
      service: whoami
  template:
    metadata:
      labels:
        type: app
        service: whoami
    spec:
      containers:
      - name: whoami
        image: subicura/whoami:1
        livenessProbe:
          httpGet:
            path: /
            port: 4567
```

* launch

```bash
$ kubectl apply -f whoami-rs-scaled.yml
```

## Launch Deployment

* [workshop-k8s-basic/guide/guide-03/task-04.md](https://github.com/subicura/workshop-k8s-basic/blob/master/guide/guide-03/task-04.md)
  * [[??????ON?????????] ??????????????? ???????????? 6??? - Kubernetes(???????????????) ?????? 1 | T????????????](https://www.youtube.com/watch?v=G0-VoHbunks&list=PLinIyjMcdO2SRxI4VmoU6gwUZr1XGMCyB&index=6)

----

### Launch Simple Deployment

* whoami-deploy.yml
  * It is almost same with ReplicaSet.
  * Deployment manages versions of ReplicaSet.

```yml
apiVersion: apps/v1beta2
kind: Deployment
metadata:
  name: whoami-deploy
spec:
  replicas: 3
  selector:
    matchLabels:
      type: app
      service: whoami
  template:
    metadata:
      labels:
        type: app
        service: whoami
    spec:
      containers:
      - name: whoami
        image: subicura/whoami:1
        livenessProbe:
          httpGet:
            path: /
            port: 4567
```

* launch

```bash
$ kubectl apply -f whoami-deploy.yml
# set image and rollout
$ kubectl set image deploy/whoami-deploy whoami=subicura/whoami:2
# rollout with files
$ kubectl apply -f whoami-deploy.yml
# get replicaset and watch continuously
$ kubectl get rs -w
# describe deploy
$ kubectl describe deploy/whoami-deploy
# show history
$ kubectl rollout history deploy/whoami-deploy
$ kubectl rollout history -f whoami-deploy.yml
$ kubectl set image deploy/whoami-deploy whoami=subicura/whoami:1 --record=true
$ kubectl rollout history -f whoami-deploy.yml
$ kubectl rollout history -f whoami-deploy.yml --revision=2
$ kubectl rollout status deploy/whoami-deploy
# inspect rolling update status and watch continuously
$ kubectl rollout status deploy/whoami-deploy -w
# rollback to prev version
$ kubectl rollout undo deploy/whoami-deploy
# rollback to specific version
$ kubectl rollout undo deploy/whoami-deploy --to-revision=3
```

### Launch Deployment with RollingUpdate

* whoami-deploy-strategy.yml

```yml
apiVersion: apps/v1beta2
kind: Deployment
metadata:
  name: whoami-deploy
spec:
  replicas: 3
  selector:
    matchLabels:
      type: app
      service: whoami
  minReadySeconds: 5
  strategy:
    type: RollingUpdate
    rollingUpdate:
      maxSurge: 1
      maxUnavailable: 1
  template:
    metadata:
      labels:
        type: app
        service: whoami
    spec:
      containers:
      - name: whoami
        image: subicura/whoami:1
        livenessProbe:
          httpGet:
            path: /
            port: 4567
```

* launch

```bash
$ kubectl apply -f whoami-deploy-strategy
$ kubectl describe deploy/whoami-deploy
$ kubectl set image deploy/whoami-deploy whoami=subicura/whoami:2
$ kubectl get rs -w
```

## Launch Service

* [workshop-k8s-basic/guide/guide-03/task-05.md](https://github.com/subicura/workshop-k8s-basic/blob/master/guide/guide-05/task-05.md)
  * [[??????ON?????????] ??????????????? ???????????? 7??? - Kubernetes(???????????????) ?????? 2 | T????????????](https://www.youtube.com/watch?v=v6TUgqfV3Fo&list=PLinIyjMcdO2SRxI4VmoU6gwUZr1XGMCyB&index=7)
* [Kubernetes NodePort vs LoadBalancer vs Ingress? When should I use what?](https://medium.com/google-cloud/kubernetes-nodeport-vs-loadbalancer-vs-ingress-when-should-i-use-what-922f010849e0)

----

* ClusterIP is used for internal communication.
* NodePort is used for external communication???

### Launch Simple Service

* redis-app.yml

```yml
apiVersion: apps/v1beta2
kind: Deployment
metadata:
  name: redis
spec:
  selector:
    matchLabels:
      type: db
      service: redis
  template:
    metadata:
      labels:
        type: db
        service: redis
    spec:
      containers:
      - name: redis
        image: redis
        ports:
        - containerPort: 6379
          protocol: TCP
---
# This is for ClusterIP
# ClusterIP is used for internal communication
apiVersion: v1
kind: Service
metadata:
  name: redis
spec:
  ports:
  - port: 6379
    protocol: TCP
  selector:
    type: db
    service: redis
```

* whoami.yml

```yml
apiVersion: apps/v1beta2
kind: Deployment
metadata:
  name: whoami
spec:
  selector:
    matchLabels:
      type: app
      service: whoami
  template:
    metadata:
      labels:
        type: app
        service: whoami
    spec:
      containers:
      - name: whoami
        image: subicura/whoami-redis:1
        env:
        - name: REDIS_HOST
          value: "redis"
        - name: REDIS_PORT
          value: "6379"
```

* launch

```bash
$ kubectl apply -f redis-app.yml
$ kubectl apply -f whoami.yml
$ kubectl get ep
$ kubectl exec -it whoami-<xxxxx> sh
  apk add curl busybox-extras # install telnet
  curl localhost:4567
  curl localhost:4567
  telnet localhost 6379
  telnet redis 6379
    dbsize
    KEYS *
    GET count
    quit
```

### Launch Service with NodePort

* whoami-svc.yml

```yml
apiVersion: v1
kind: Service
metadata:
  name: whoami
spec:
  type: NodePort
  ports:
  - port: 4567
    protocol: TCP
  selector:
    type: app
    service: whoami
```

## Launch LoadBalancer

* [workshop-k8s-basic/guide/guide-03/task-06.md](https://github.com/subicura/workshop-k8s-basic/blob/master/guide/guide-05/task-06.md)
  * [[??????ON?????????] ??????????????? ???????????? 7??? - Kubernetes(???????????????) ?????? 2 | T????????????](https://www.youtube.com/watch?v=v6TUgqfV3Fo&list=PLinIyjMcdO2SRxI4VmoU6gwUZr1XGMCyB&index=7)

----

### Launch Simple LoadBalancer

* whoami-app.yml
  * If you launch this on AWS, ELB will attached to service.
  * NodePort is just a external port of Node But LoadBalancer is external reousrce to load balances.

```yml
apiVersion: apps/v1beta2
kind: Deployment
metadata:
  name: whoami
spec:
  selector:
    matchLabels:
      type: app
      service: whoami
  template:
    metadata:
      labels:
        type: app
        service: whoami
    spec:
      containers:
      - name: whoami
        image: subicura/whoami-redis:1
        env:
        - name: REDIS_HOST
          value: "redis"
        - name: REDIS_PORT
          value: "6379"
---

apiVersion: v1
kind: Service
metadata:
  name: whoami
spec:
  type: LoadBalancer
  ports:
  - port: 8000
    targetPort: 4567
    protocol: TCP
  selector:
    type: app
    service: whoami

---

apiVersion: apps/v1beta2
kind: Deployment
metadata:
  name: redis
spec:
  selector:
    matchLabels:
      type: db
      service: redis
  template:
    metadata:
      labels:
        type: db
        service: redis
    spec:
      containers:
      - name: redis
        image: redis
        ports:
        - containerPort: 6379
          protocol: TCP
---

apiVersion: v1
kind: Service
metadata:
  name: redis
spec:
  ports:
  - port: 6379
    protocol: TCP
  selector:
    type: db
    service: redis
```

* launch

```bash
```

### ????

* whoami-svc-v1-v2.yml

```yml
apiVersion: apps/v1beta2
kind: Deployment
metadata:
  name: whoami-v1
spec:
  replicas: 3
  selector:
    matchLabels:
      type: app
      service: whoami
      version: v1
  template:
    metadata:
      labels:
        type: app
        service: whoami
        version: v1
    spec:
      containers:
      - name: whoami
        image: subicura/whoami:1
        livenessProbe:
          httpGet:
            path: /
            port: 4567

---

apiVersion: apps/v1beta2
kind: Deployment
metadata:
  name: whoami-v2
spec:
  replicas: 3
  selector:
    matchLabels:
      type: app
      service: whoami
      version: v2
  template:
    metadata:
      labels:
        type: app
        service: whoami
        version: v2
    spec:
      containers:
      - name: whoami
        image: subicura/whoami:2
        livenessProbe:
          httpGet:
            path: /
            port: 4567
```

* launch

```bash
```

### ???

* whoami-svc-v1.yml

```yml
apiVersion: v1
kind: Service
metadata:
  name: whoami
spec:
  type: LoadBalancer
  ports:
  - port: 8000
    targetPort: 4567
    protocol: TCP
  selector:
    type: app
    service: whoami
    version: v1
```

* launch

```bash
```

### ???

* whoami-svc-all.yml

```yml
apiVersion: v1
kind: Service
metadata:
  name: whoami
spec:
  type: LoadBalancer
  ports:
  - port: 8000
    targetPort: 4567
    protocol: TCP
  selector:
    type: app
    service: whoami
```

* launch

```bash
```

## Launch Ingress

* [workshop-k8s-basic/guide/guide-03/bonus.md](https://github.com/subicura/workshop-k8s-basic/blob/master/guide/guide-05/bonus.md)
  * [[??????ON?????????] ??????????????? ???????????? 7??? - Kubernetes(???????????????) ?????? 2 | T????????????](https://www.youtube.com/watch?v=v6TUgqfV3Fo&list=PLinIyjMcdO2SRxI4VmoU6gwUZr1XGMCyB&index=7)

----

* Ingress is a mapping between DNS to internals.
* You can use IP as DNS
  * [sslip.io](https://sslip.io/)
  * [nip.io](https://nip.io/)

```bash
10.0.0.1.nip.io maps to 10.0.0.1
192-168-1-250.nip.io maps to 192.168.1.250
app.10.8.0.1.nip.io maps to 10.8.0.1
app-37-247-48-68.nip.io maps to 37.247.48.68
customer1.app.10.0.0.1.nip.io maps to 10.0.0.1
customer2-app-127-0-0-1.nip.io maps to 127.0.0.1
```

### Launch Simple Ingress

* whoami-v1.yml

```yml
apiVersion: extensions/v1beta1
kind: Ingress
metadata:
  name: whoami-v1
  annotations:
    ingress.kubernetes.io/rewrite-target: "/"
    ingress.kubernetes.io/ssl-redirect: "false"
spec:
  rules:
  - host: v1.whoami.13.125.41.102.sslip.io
    http:
      paths: 
      - path: /
        backend:
          serviceName: whoami-v1
          servicePort: 4567

---

apiVersion: apps/v1beta2
kind: Deployment
metadata:
  name: whoami-v1
spec:
  replicas: 3
  selector:
    matchLabels:
      type: app
      service: whoami
      version: v1
  template:
    metadata:
      labels:
        type: app
        service: whoami
        version: v1
    spec:
      containers:
      - name: whoami
        image: subicura/whoami:1
        livenessProbe:
          httpGet:
            path: /
            port: 4567

---

apiVersion: v1
kind: Service
metadata:
  name: whoami-v1
spec:
  ports:
  - port: 4567
    protocol: TCP
  selector:
    type: app
    service: whoami
    version: v1
```

* launch

```bash
$ kubectl get ingress
```

### ????

* whoami-v2.yml

```yml
apiVersion: extensions/v1beta1
kind: Ingress
metadata:
  name: whoami-v2
  annotations:
    ingress.kubernetes.io/rewrite-target: "/"
    ingress.kubernetes.io/ssl-redirect: "false"
spec:
  rules:
  - host: v2.whoami.13.125.41.102.sslip.io
    http:
      paths: 
      - path: /
        backend:
          serviceName: whoami-v2
          servicePort: 4567

---

apiVersion: apps/v1beta2
kind: Deployment
metadata:
  name: whoami-v2
spec:
  replicas: 3
  selector:
    matchLabels:
      type: app
      service: whoami
      version: v2
  template:
    metadata:
      labels:
        type: app
        service: whoami
        version: v2
    spec:
      containers:
      - name: whoami
        image: subicura/whoami:2
        livenessProbe:
          httpGet:
            path: /
            port: 4567

---

apiVersion: v1
kind: Service
metadata:
  name: whoami-v2
spec:
  ports:
  - port: 4567
    protocol: TCP
  selector:
    type: app
    service: whoami
    version: v2
```

## Launch Horizontal Pod Autoscaler

* [workshop-k8s-basic/guide/guide-03/task-06.md](https://github.com/subicura/workshop-k8s-basic/blob/master/guide/guide-05/task-06.md)
  * [[??????ON?????????] ??????????????? ???????????? 7??? - Kubernetes(???????????????) ?????? 2 | T????????????](https://www.youtube.com/watch?v=v6TUgqfV3Fo&list=PLinIyjMcdO2SRxI4VmoU6gwUZr1XGMCyB&index=7)

----

### Launch Simple Horizontal Pod Autoscaler

* hpa-example-deploy.yml.yml

```yml
apiVersion: apps/v1beta2
kind: Deployment
metadata:
  name: hpa-example-deploy
spec:
  selector:
    matchLabels:
      type: app
      service: hpa-example
  template:
    metadata:
      labels:
        type: app
        service: hpa-example
    spec:
      containers:
      - name: hpa-example
        image: k8s.gcr.io/hpa-example
        resources:
            limits:
              cpu: "0.5"
            requests:
              cpu: "0.25"
---

apiVersion: v1
kind: Service
metadata:
  name: hpa-example
spec:
  ports:
  - port: 80
    protocol: TCP
  selector:
    type: app
    service: hpa-example
```

* hpa.yml

```yml
apiVersion: autoscaling/v1
kind: HorizontalPodAutoscaler
metadata:
  name: hpa-example
spec:
  maxReplicas: 4
  minReplicas: 1
  scaleTargetRef:
    apiVersion: extensions/v1
    kind: Deployment
    name: hpa-example-deploy
  targetCPUUtilizationPercentage: 10
```

## Launch Kubernetes Dashboard

```bash
# show k8s client, server version
$ kubectl version --output yaml
# show contexts, default context is docker-for-desktop
$ kubectl config get-contexts
# show nodes
$ kubectl get nodes
# show pods
$ kubectl get pods --all-namespaces
# launch k8s dashbaord
$ kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v1.10.1/src/deploy/recommended/kubernetes-dashboard.yaml
# show services
$ kubectl get services --all-namespaces
# launch proxy server to connect k8s dashboard
$ kubectl proxy
# open http://localhost:8001/api/v1/namespaces/kube-system/services/https:kubernetes-dashboard:/proxy/
# create sample user and login. 
# - https://github.com/kubernetes/dashboard/blob/master/docs/user/access-control/creating-sample-user.md
```

* `https://raw.githubusercontent.com/kubernetes/dashboard/v1.10.1/src/deploy/recommended/kubernetes-dashboard.yaml`

```yaml
# Copyright 2017 The Kubernetes Authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# ------------------- Dashboard Secret ------------------- #

apiVersion: v1
kind: Secret
metadata:
  labels:
    k8s-app: kubernetes-dashboard
  name: kubernetes-dashboard-certs
  namespace: kube-system
type: Opaque

---
# ------------------- Dashboard Service Account ------------------- #

apiVersion: v1
kind: ServiceAccount
metadata:
  labels:
    k8s-app: kubernetes-dashboard
  name: kubernetes-dashboard
  namespace: kube-system

---
# ------------------- Dashboard Role & Role Binding ------------------- #

kind: Role
apiVersion: rbac.authorization.k8s.io/v1
metadata:
  name: kubernetes-dashboard-minimal
  namespace: kube-system
rules:
  # Allow Dashboard to create 'kubernetes-dashboard-key-holder' secret.
- apiGroups: [""]
  resources: ["secrets"]
  verbs: ["create"]
  # Allow Dashboard to create 'kubernetes-dashboard-settings' config map.
- apiGroups: [""]
  resources: ["configmaps"]
  verbs: ["create"]
  # Allow Dashboard to get, update and delete Dashboard exclusive secrets.
- apiGroups: [""]
  resources: ["secrets"]
  resourceNames: ["kubernetes-dashboard-key-holder", "kubernetes-dashboard-certs"]
  verbs: ["get", "update", "delete"]
  # Allow Dashboard to get and update 'kubernetes-dashboard-settings' config map.
- apiGroups: [""]
  resources: ["configmaps"]
  resourceNames: ["kubernetes-dashboard-settings"]
  verbs: ["get", "update"]
  # Allow Dashboard to get metrics from heapster.
- apiGroups: [""]
  resources: ["services"]
  resourceNames: ["heapster"]
  verbs: ["proxy"]
- apiGroups: [""]
  resources: ["services/proxy"]
  resourceNames: ["heapster", "http:heapster:", "https:heapster:"]
  verbs: ["get"]

---
apiVersion: rbac.authorization.k8s.io/v1
kind: RoleBinding
metadata:
  name: kubernetes-dashboard-minimal
  namespace: kube-system
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: Role
  name: kubernetes-dashboard-minimal
subjects:
- kind: ServiceAccount
  name: kubernetes-dashboard
  namespace: kube-system

---
# ------------------- Dashboard Deployment ------------------- #

kind: Deployment
apiVersion: apps/v1
metadata:
  labels:
    k8s-app: kubernetes-dashboard
  name: kubernetes-dashboard
  namespace: kube-system
spec:
  replicas: 1
  revisionHistoryLimit: 10
  selector:
    matchLabels:
      k8s-app: kubernetes-dashboard
  template:
    metadata:
      labels:
        k8s-app: kubernetes-dashboard
    spec:
      containers:
      - name: kubernetes-dashboard
        image: k8s.gcr.io/kubernetes-dashboard-amd64:v1.10.1
        ports:
        - containerPort: 8443
          protocol: TCP
        args:
          - --auto-generate-certificates
          # Uncomment the following line to manually specify Kubernetes API server Host
          # If not specified, Dashboard will attempt to auto discover the API server and connect
          # to it. Uncomment only if the default does not work.
          # - --apiserver-host=http://my-address:port
        volumeMounts:
        - name: kubernetes-dashboard-certs
          mountPath: /certs
          # Create on-disk volume to store exec logs
        - mountPath: /tmp
          name: tmp-volume
        livenessProbe:
          httpGet:
            scheme: HTTPS
            path: /
            port: 8443
          initialDelaySeconds: 30
          timeoutSeconds: 30
      volumes:
      - name: kubernetes-dashboard-certs
        secret:
          secretName: kubernetes-dashboard-certs
      - name: tmp-volume
        emptyDir: {}
      serviceAccountName: kubernetes-dashboard
      # Comment the following tolerations if Dashboard must not be deployed on master
      tolerations:
      - key: node-role.kubernetes.io/master
        effect: NoSchedule

---
# ------------------- Dashboard Service ------------------- #

kind: Service
apiVersion: v1
metadata:
  labels:
    k8s-app: kubernetes-dashboard
  name: kubernetes-dashboard
  namespace: kube-system
spec:
  ports:
    - port: 443
      targetPort: 8443
  selector:
    k8s-app: kubernetes-dashboard
```

# Dive Deep

## controller

* [A deep dive into Kubernetes controllers](https://engineering.bitnami.com/articles/a-deep-dive-into-kubernetes-controllers.html)
  * [sample-controller @ github](https://github.com/kubernetes/sample-controller)
    * pretty simple custom controller
  * [kubewatch @ github](https://github.com/bitnami-labs/kubewatch)
    * controller which sends slack messages

----

![](img/client-go-controller-interaction.jpeg)

* kubernetes ??? controller ??? ????????? kubernetes resource ??? ???????????? ????????? desired state ??? ????????????.
  ```go
  for {
    desired := getDesiredState()
    current := getCurrentState()
    makeChanges(desired, current)
  }
  ```
* controller ??? ?????? ??????????????? Informer/SharedInformer ??? Workqueue ??? ??????.
* Informer/SharedInformer ??? desired state ??? ???????????? Workqueue ??? ???????????? ?????? ????????????.
* kube-controller-manager ??? ?????? ?????? controller ?????? ????????????. ??? controller ??? ???????????? ???????????? ?????? resource ??? ???????????? polling ?????? caching ??????. ??? cache ??? controller ?????? ?????? ????????????. SharedInformer ??? ?????? ?????? ????????? cache ??? ????????????. ????????? SharedInformer ??? Informer ?????? ??? ?????? ????????????. 
* Worker thread ??? Workqueue ?????? ???????????? ?????? ????????? ????????????.

## API server

* [Kubernetes API Reference](https://kubernetes.io/docs/reference/)

## Monitoring

* [Prometheus with Kubernetes](https://www.slideshare.net/jinsumoon33/kubernetes-prometheus-monitoring)

