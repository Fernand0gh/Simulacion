import random
import math

op=int(input("Indica cómo quieres elegir la cantidad de numeros a generar \n 1)Teclado 2)Random"))

n=0
if op==1:
    n=int(input('Ingresa la cantidad de números a generar'))
elif op==2:
    n=random.randint(1, 1000)
    print("La cantidad de números a generar es de: " , n)

numbers=[]

print(f"{'i':7}{'x     ':15}")

for i in range(n):
        numbers.append(random.randint(-100000, 100000))
        print(f"{(i+1):7}{numbers[i]:15}")

suma=0
for x in numbers:
    suma+=x
media=suma/n
print("La media es: " , media)

suma2=0
for x in numbers:
    suma2+=math.pow((x-media), 2)
varianza=suma2/n
print("La varianza es de: ", varianza)



