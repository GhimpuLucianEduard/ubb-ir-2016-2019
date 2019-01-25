#!/bin/bash

# Ghimpu Lucian Eduard gr213 2/2
# Proiect AMS 27b

# Sa se scrie un fisier de comenzi care preia un fisier de intrare
# dat ca parametru si creeaza din el un alt fisier (al carui nume
# este dat ca parametru) in care pastreaza doar cuvintele care contin
# litere mici. Fisierul se va ordona alfabetic. Daca in rezultat exista
# linii consecutive identice, se va pastra doar una dintre ele.

#verificam daca se dau 2 parametrii

if [ ! $# -eq 2 ]
then 
	echo usage: 27b.sh file newFileName
	exit 1
fi

#verificam daca primul parametru este un fisier

if [ ! -f $1 ]
then
	echo $1 nu este un fisier!
	exit 1
fi

#cream un fisier temporar
touch buffer

#luam doar cuvintele potrivite
grep -o "\<[a-z]*\>" $1 >> buffer

#sortam si redenumim
sort buffer | uniq >> altBuffer #altBuffer va fi fisierul final
rm buffer 
mv altBuffer $2

