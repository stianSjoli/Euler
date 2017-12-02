import Data.List
import Data.Numbers.Primes 

digits:: Int->[Int]

glueDigits :: [Int] -> Int 

rotate:: [Int] -> [[Int]]

circularPrime:: Int-> Bool

digits 0 = []

digits x = (x `mod` 10): digits(x `div` 10)

glueDigits x = read $ concat $ map (show) x

rotate (x:xs) = rotated : rotate rotated
	where 
		rotated = xs ++ [x]	

circularPrime prime = all isPrime primeSuspects 
	where 
		primeSuspects = map glueDigits rotated
		rotated = take rotations (rotate primeDigits)
		rotations = length $ digits prime
		primeDigits = reverse $ digits prime 

main = print $ length $ filter circularPrime [1..1000000]