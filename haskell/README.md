# eulerProject
just for me to remember my solutions for the project Euler problems

--solution problem 1:

sum [n |n <- [1..999], n `mod` 5 == 0 || n `mod` 3==0]

--solution problem 2:

I checked the upper limit by pre-testing (bad way)

fib 0 = 0

fib 1 = 1 

fib n = fib (n-1) + fib (n-2)

problemTwo = sum [x | x <- map fib [2..33], even x]

--solution problem 3

import Data.Numbers.Primes

problemThree = last (primeFactors 600851475143)


--solution problem 7 

import Data.Numbers.Primes 

--index starts with 0

primes !! 10000
--solution problem 10

import Data.Numbers.Primes 

main = print $ sum (takeWhile (< 2000000) primes)

--solution problem 6 

main = do 
	let squaresSum = sum [1..100]
	let sumSquares = sum [x*x | x <- [1..100]]
	print $ squaresSum*squaresSum - sumSquares

--solution problem 35 

import Data.List
import Data.Numbers.Primes 

digits 0 = []

digits x = (x `mod` 10): digits(x `div` 10)

glueDigits :: [Int] -> Int 

glueDigits x = read $ concat $ map (show) x

rotate (f:s:xs) repeat  | repeat > 0 = rotated : rotate rotated (repeat - 1)
						| repeat == 0 = []	
	where 
		rotated = [s] ++ xs ++ [f]

circularPrime prime = all isPrime primeSuspects 
	where 
		primeSuspects = map glueDigits rotated
		rotated = rotate (reverse (digits prime)) rotations
		rotations = length $ digits prime   
	
main = do 
	let below10 = length $ map (primes !!) [0..3]
	print $ below10 + (length $ filter circularPrime [10..1000000])