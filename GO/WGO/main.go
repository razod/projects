package main

import (
	"net/http"
	"strings"
)
func ping(w http.ResponseWriter, r *http.Request) {
	w.Write([]byte("pong"))
  }
  func sayHello(w http.ResponseWriter, r *http.Request) {
	message := r.URL.Path
	message = strings.TrimPrefix(message, "/hello/")
	message = "Hello " + message
	w.Write([]byte(message))
  }

  func main() {
	http.HandleFunc("/hello/", sayHello)
	http.Handle("/static/", http.StripPrefix("/static/", http.FileServer(http.Dir("./src/static"))))
	http.Handle("/", http.FileServer(http.Dir("./src")))
	http.HandleFunc("/ping", ping)
	if err := http.ListenAndServe(":8080", nil); err != nil {
	  panic(err)
	}
  }
