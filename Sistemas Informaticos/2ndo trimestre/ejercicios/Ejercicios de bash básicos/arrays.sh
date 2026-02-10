ç#!/usr/bin/env bash

mi_array=("valor 1" "valor 2" "valor 3" "valor 4")

echo "Segundo elemento: ${mi_array[1]}"
echo "Último elemento: ${mi_array[${#mi_array[@]}-1]}"
echo "Todos los elementos: ${mi_array[@]}"
echo "Número total de elementos: ${#mi_array[@]}"

echo "Desde índice 1, tres posiciones: ${mi_array[@]:1:3}"
echo "Desde índice 2 hasta el final: ${mi_array[@]:2}"
