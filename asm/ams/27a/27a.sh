#!/bin/bash

# Ghimpu Lucian Eduard gr213 2/2
# Proiect AMS 27a

# Sa se scrie un script shell care monitorizeaza aparitia
# in toate directoarele date ca si parametru in linia de comanda
# A unui nume de fisier citit de la tastatura.



#verificam sa se fie dat cel putin un parametru
if [ $# -le 0 ]
then
	echo uasge: 27a.sh dir1 dir2 ... dirN
	exit 1
fi

#verificam ca toti parametrii sa fie directoare
for param in $@
do
	if [ ! -d $param ]
	then
		echo $param nu e director!
		exit 1
	fi
done

#citim numele fisierului pe care vrem sa-l monitorizam
echo Scrieti numele fisierului pe care vreti sa-i monitorizati aparitia
read numeFisier


#ciclu care ruleaza in background si verifica daca fisierul dat este creat
while (true) 
do
	for fis in $@
	do
		if find $fis -name $numeFisier | grep -q "$numeFisier"
		then
			echo The file $numeFisier  has been created!!!
			exit
		fi
	done
done &

	
