#|
Sa se insereze intr-o lista liniara un atom a dat dupa al 2-lea, al 4-lea,
al 6-lea,....element.
|#

(defun inserarePar(lista element poz)
	(cond
		((null lista) nil)
		((= (mod poz 2) 1) (cons (car lista) (inserarePar (cdr lista) element (+ 1 poz))))
		(t (cons (car lista) (cons element (inserarePar (cdr lista) element (+ 1 poz)))))
	)
)

#|
Definiti o functie care obtine dintr-o lista data lista tuturor atomilor
care apar, pe orice nivel, dar in ordine inversa. De exemplu: 
(((A B) C) (D E)) --> (E D C B A)

((listp (car lista)) (append (atomiInvers (cdr lista))) (atomiInvers (car lista)))
|#



(defun atomiInvers(lista)
	(cond
		((null lista) nil)
		((numberp (car lista)) (append (atomiInvers (cdr lista)) (list (car lista))))
		((listp (car lista)) (append (atomiInvers (cdr lista)) (atomiInvers (car lista))))
	)
)

#|
Definiti o functie care intoarce cel mai mare divizor comun al numerelor
dintr-o lista neliniara.
|#

 
 (defun cmmdc(nr1 nr2)
 	(cond
 		((null nr1) nr2)
 		((null nr2) nr1)
 		((= nr1 0)  nr2)
 		((= nr2 0)  nr1)
 		(t (cmmdc nr2 (mod nr1 nr2)))
 	)
 )

(defun cmmmdcLista(lista)
	(cond
		((null lista) nil)
		(t (cmmdc (car lista) (cmmmdcLista (cdr lista)) ))
	)
)


#|
Sa se scrie o functie care determina numarul de aparitii ale unui atom dat
intr-o lista neliniara.
|#

(defun aparitiiNeliniar(lista element) 
	(cond 
		((null lista) 0)
		((= element (car lista)) (+ (aparitiiNeliniar (cdr lista) element) 1) )
		(t (aparitiiNeliniar (cdr lista) element))
	)
)

#|
 Sa se decida daca un arbore de tipul (2) este echilibrat (diferenta dintre
 adancimile celor 2 subarbori nu este mai mare decat 1).
|#


(defun adancime(l)
 (cond
   ((null l) 0)
   ((atom (car l)) (adancime (cdr l)))
   (t (max (+ 1 (adancime (car l))) (adancime (cdr l))))
 )
)


#| postortidne |#

(defun postordine (a)
  (cond
    ((null a) nil)
    (t (append (postordine (cadr a)) (postordine (caddr a)) (list (car a))))
  )
)

#| preordine |#

(defun preordine (a)
  (cond
    ((null a) nil)
    (t (append (list (car a)) (preordine (cadr a)) (preordine (caddr a)) ))
  )
)

#| inordine |#

(defun inordine (a)
  (cond
    ((null a) nil)
    (t (append (inordine (cadr a))  (list (car a))  (inordine (caddr a)) ))
  )
)

#| Se da un arbore de tipul (2). Sa se afiseze nivelul (si lista corespunza
toare a nodurilor) avand numar maxim de noduri. Nivelul rad. se considera 0.
|#

(defun contine(tree element) 
	(cond 
		((null tree) nil)
		((= (car tree) element) (car tree))
		(t (or (contine (cadr tree) element) (contine (caddr tree) element) ))
	)
)

(defun drum(tree element)
	(cond 
		((null tree) nil)
		((not (null (contine (cadr tree) element)))  (append (list(car (cadr tree))) (drum (cadr tree) element)) )
		((not (null (contine (caddr tree) element)))  (append (list(car (caddr tree))) (drum (caddr tree) element)) )
	)
)




#|  Se da un arbore de tipul (2). Sa se precizeze nivelul pe care apare un nod |#

(defun nivel(tree element)
	(cond 
		((null tree) -100)
		((equal (car tree) element) 0)
		(t (max (+ 1 (nivel (cadr tree) element)) (+ 1 (nivel (caddr tree) element)))) 
	)
)


#| Sa se tipareasca lista nodurilor de pe nivelul k dintr-un arbore de tipul 2|#

(defun listaNivel(tree nivel nivelActual)
	(cond 
		((null tree) nil)
		((equal nivel nivelActual) (list (car tree)))
		(t (append (listaNivel (cadr tree) nivel (+ 1 nivelActual)) (listaNivel (caddr tree) nivel (+ 1 nivelActual))))
	)
)

#| sortare lista liniara |#

(defun insert(lista element)
	(cond 
		((null lista) (list element))
		((< element (car lista)) (cons element lista))
		(t (cons (car lista) (insert (cdr lista) element )))
	)
)


(defun sortare(lista)
	(cond
		((null  lista) nil)
		((null (cdr lista)) (list (car lista)))
		(t (insert (sortare (cdr lista) ) (car lista)))
	)
)

#| ultimul elemnt al fiecare subliste |#

(defun ultim(lista)
	(cond
		((null lista) nil)
		((null (cdr lista)) (car lista))
		(t (ultim (cdr lista)))
	)
)

(defun ultimNeliar(lista)
	(cond
		((null lista) nil)
		((and (null (cdr lista)) (atom (car lista))) (list (car lista)) )
		(t (append (ultim)))
	)
)