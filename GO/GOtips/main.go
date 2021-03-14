package main

import (
	"errors"
	"fmt"
	"math"
)

func variables() {
	var x int = 2
	var y int
	y = 3
	var sum = x + y
	// Neater Variables
	a := 2
	b := 5
	sum2 := a + b
	fmt.Println(sum)
	fmt.Println(sum2)

	// Conditions

	if x > 1 {
		fmt.Println("x is greater than 1")
	} else if x < 1 {
		fmt.Println("x is less than 1")
	} // else {}

}

func arrays() {
	var a [5]int // 5 integers in an array, defaulted to 0
	a[2] = 7     // set pos 2 to 7 (arrays start at pos 0 like normal)
	fmt.Println(a)

	// Neater
	b := [5]int{5, 4, 3, 8, 7} // fixed array, cannot be changed
	fmt.Println(b)

	c := []int{5, 4, 3, 8, 7} // unfixed array, can be changed
	c = append(c, 13)         // append to array
	fmt.Println(c)

}

func maps() {
	vertices := make(map[string]int)
	vertices["triangle"] = 2
	vertices["square"] = 3
	fmt.Println(vertices)
	// delete(vertices, "triangle") // delete key
	fmt.Println(vertices["square"]) // get value for certain key

}

func loops() {
	// for loop
	for i := 0; i < 5; i++ {
		fmt.Println(i)
	}
	// can also be used as a while loop
	ii := 0
	for ii < 5 {
		fmt.Println(ii)
		ii++
	}
	// iterate each element in array
	// can be used for maps too, just change "index" to "key"
	arr := []string{"a", "b", "c"}
	for index, value := range arr {
		fmt.Println("index:", index, "value:", value)
	}
}

// example functions:

func sum(x int, y int) int /* this is the return value */ {
	return x + y
}

func squareRoot(x float64) (float64, error) {
	if x < 0 {
		return 0, errors.New("Undefined for negative numbers")
	}
	return math.Sqrt(x), nil
}

// types

// Person ...
type Person struct {
	name string
	age  int
}

func types() {
	p := Person{name: "John", age: 28}
	fmt.Println(p)
	// can do p.name or p.age
}

// pointers 
/* If you did
func point() {
	num := 5
	inc(num)
	fmt.Println(num)
}
func inc(x int) {
	x++
}
x would still equal 5
so do:
*/

func pointers() {
	num := 5
	inc(&num)
	fmt.Println(num)
}

func inc(x *int) {
	*x++
}


func main() {
	variables()
	arrays()
	maps()
	loops()
	types()
	pointers()
	res := sum(5, 4)
	res2, err := squareRoot(5)
	// res2, err := squareRoot(-5) would result in error
	fmt.Println(res)
	fmt.Println("Hello World")
	if err != nil {
		fmt.Println(err)
	} else {
		fmt.Println(res2)
	}
}
