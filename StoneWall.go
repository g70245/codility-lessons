package solution

// you can also use imports, for example:
// import "fmt"
// import "os"
import (
    "fmt"
)
// you can write to stdout for debugging purposes, e.g.
// fmt.Println("this is a debug message")

func Solution(H []int) int {
    // write your code in Go 1.4
    fmt.Println(H)
    stack := make([]int, 0)

    ans := 0
    for _, h := range H {
        // fmt.Println(stack, " ", ans)
        if len(stack) == 0 {
            stack = append(stack, h)
            continue
        }

        last := len(stack) - 1
        if h > stack[last] {
            stack = append(stack, h)
        }else if h < stack[last] {
            for last >= 0 && h < stack[last] {
                stack = stack[:last]
                ans++
                last--
            }

            if len(stack) == 0 || h > stack[last] {
                stack = append(stack, h)
            }
        }
    }

    return ans + len(stack)
}
