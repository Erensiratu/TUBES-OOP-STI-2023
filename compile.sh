#!/bin/bash
# Compile
javac -d bin -cp bin src/*.java

# Check
if [ $? -eq 0 ]
then
    echo "Kompilasi Berhasil, Program Akan Dimulai"

    # Run
    java -cp bin SimPlicity
else
    echo "Kompilasi Gagal"
fi