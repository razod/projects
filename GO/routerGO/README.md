# routergo
a simple router in go, ik there are better routers lol

### How to get RouterGo

```go get gopkg.in/razod/routergo.v0```

```import "gopkg.in/razod/routergo.v0"```

### Example

```js
package main

import (
	"net/http"

	"gopkg.in/razod/routergo.v0"
)

func ping(w http.ResponseWriter, r *http.Request) {
	w.Write([]byte("pong"))
}

func main() {
	routergo.HandleFunc("/ping", ping)
	routergo.NewServ("/", "./src", "5000")
	routergo.HandleStatic("/static/", "./src/static")
}
```
