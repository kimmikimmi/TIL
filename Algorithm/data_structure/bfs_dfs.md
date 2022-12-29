# BFS / DFS 

## Time Complexity: 
  $\Theta(|V| + |E|)$

```
Algorithm BFS (G, s)
{
    for each  v in V - {s}
        visited[s] <- no
    visited[s] <- yes
    enqueue(Q, s)

    while (Q is not empty) {
        u <- dequeue(Q)
        for each v in L(u)
            if (visited[v] == no) {
                visited[v] <- yes
                enqueue(Q, v)
            }
    }

}
```

```
Algorithm DFS (v)
{
    visited[v] = yes
    for u in L(v) {
        if (visited[u] == no) {
            visited[u] = yes
            DFS(u);
        }
    }
}
```
