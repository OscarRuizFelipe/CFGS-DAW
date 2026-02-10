#!/usr/bin/env bash

if [ -z "$1" ]; then
  read -p "¿Cómo te llamas? " nombre
else
  nombre="$1"
fi

echo "Hola, $nombre "
