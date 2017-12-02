
fib 0 = 0

fib 1 = 1 

fib n = fib (n-1) + fib (n-2)

problemTwo = sum [x | x <- map fib [2..33], even x]