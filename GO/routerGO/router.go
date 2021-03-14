package routergo

import (
	"fmt"
	"net/http"
)

// HandleFunc registers a handler function
// pretty neat!
func HandleFunc(path string, f func(w http.ResponseWriter, r *http.Request)) {
	http.HandleFunc(path, f)
}

// HandleStatic opens a file server for the static files
// pretty cool!
func HandleStatic(path string, dir string) {
	http.Handle(path, http.StripPrefix(path, http.FileServer(http.Dir(dir))))
}

// NewServ opens a server for html files.
// pretty sick!
func NewServ(path string, dir string, port string) {
	http.Handle(path, http.FileServer(http.Dir(dir)))
	if err := http.ListenAndServe(":"+port, nil); err != nil {
		panic(err)
	}
	fmt.Println("Server started on port " + port)
}
