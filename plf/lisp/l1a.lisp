#| 
a) Sa se scrie de doua ori elementul de pe pozitia a n-a a unei liste
liniare. De exemplu, pentru (10 20 30 40 50) si n=3 se va produce (10 20
30 30 40 50).

						 { null, daca l1..ln = null
dublare(l1...ln, i, n) = { l1 U l1 U dublare(l2...ln, i+1, n), daca i==n
						 { l1 U dublare(l2...ln, i+1, n), daca i!=n

|#



(defun dublare (l i j)
	(cond
		((null l) nil)
		((= i j) (cons (car l) (cons (car l) (dublare (cdr l) (+ i 1) j))))
		(t (cons (car l) (dublare (cdr l) (+ i 1) j)))
	)
)
	
(defun dublareAux (l n)
	(dublare l 0 n)
)

#| 
Sa se scrie o functie care realizeaza o lista de asociere cu cele doua
liste pe care le primeste. De ex: (A B C) (X Y Z) --> ((A.X) (B.Y)
(C.Z)).
							 
perechi(l1...ln l1'...ln') = { null, daca l1=null sau l2=null
							 { (l1 U l1') U perechi(l2...ln, l2'...ln')

|#

(defun perechi (l1 l2) 
	(cond
		((or (null l1) (null l2)) nil)
		(t (cons (cons (car l1) (car l2)) (perechi (cdr l1) (cdr l2))))
	)
)


#|
Sa se determine numarul tuturor sublistelor unei liste date, pe orice
nivel. Prin sublista se intelege fie lista insasi, fie un element de pe
orice nivel, care este lista. Exemplu: (1 2 (3 (4 5) (6 7)) 8 (9 10)) =>
5 (lista insasi, (3 ...), (4 5), (6 7), (9 10)).
					
					{ 1, daca l=null
nrListe(l1...ln) =  { nrListe(l2...ln), daca l1 nu e lista
					{ nrListe(l1) + nrliste(l2...ln), daca l1 e lista
|#

(defun nrListe (l)
	(cond
		((null l) 1)
		((atom (car l)) (nrListe(cdr l)))
		(t (+ (nrListe (car l)) (nrListe (cdr l))))  
	)
)


#| 
Sa se construiasca o functie care intoarce numarul atomilor dintr-o lista,
de la nivel superficial.
					{ 0, daca l=null
nrAtomi(l1...ln) =  { 1 + nrAtomi(l2...ln), daca l1 e atom
					{ nrAtomi(l2...ln), daca l1 nu e atom
|#

(defun nrAtomi (l)
	(cond 
		((null l) 0)
		((atom (car l)) (+ 1 (nrAtomi (cdr l))))
		(t (nrAtomi (cdr l)))
	)
)




