;adancimea unei liste
(defun adancime(l)
  (cond
    ((null l) 0)
    ((atom l) 0)
    (t (+ 1 (apply 'max (mapcar 'adancime l))))
  )
)

;toti atomii de pe orice nivel
(defun atomi(l)
	(cond
		((null l) nil)
		((atom l) (list l))
		(t (mapcan 'atomi l))
	)
)

;element daca apartine

(defun apartine(l e)
	(cond
		((null l) nil)
		((and (atom l) (= l e)) (list 1))
		((and (atom l) (not (= l e))) (list 0))
		(t  (mapcan #'(lambda (r) (apartine r e)) l))
		
	)

)

(defun mainApartine(l e)
	(apply '+ (apartine l e))
)


;suma atomilor numerici

(defun sumaNumerici(l)
	(cond 
		((null l) nil)
		((numberp l) (list l))
		((atom l) (list 0) )
		(t (mapcan 'sumaNumerici l))
	)
)

(defun mainNumerici(l) (apply '+ (sumaNumerici l)))


;inversa unei liste liniare

(defun inversaLiniara(l)
	(cond 
		((null l) nil)
		(t (append (inversaLiniara (cdr l)) (list (car l)) ) )
	)
)


(defun invers(l)
  (cond
    ((null l) nil)
    ((atom l) l)
    (t (inversaLiniara (mapcar 'invers l)))
  )
)

;nr atomilor unei liste

(defun nrAtomiMap(l)
	(cond
		((null l) 0)
		((atom l) 1)
		(t (apply '+ (mapcar 'nrAtomiMap l) ) )
	)
)

;adancime arbore n-ar

(defun adancime2(a)
	(cond
		((null a) 0)
		((atom a) 0)
		(t (+ 1 (apply 'max (mapcar 'adancime2 a))))
	)
)

#|
5.Se da o lista neliniara. Sa se scrie un program LISP pentru determinarea numarului de subliste de la orice nivel, 
pentru care atomul numeric minim de pe nivelurile impare este par - nivelul superficial se considera 1. 
Prelucrarea se va face cu functii MAP. De ex: lista (A(B 2)(2 C 4) (1(3 F)) (((G) 4)2)) are 3 astfel de subliste:
(B 2) , (2 C 4) si (((G)4)2).
|#

(defun parImpar(l n)
	(cond
		((null l) nil)
		((and (atom l) (= (mod l 2) 0) ) l)
	)
)

(defun subs(l n)
	(cond 
		((atom l) nil)
		((and (= (mod (minim l) 2) 0) (= (mod n 2)1 ) ) (cons l (mapcar #'(lambda (x) (subs x n)) l)))
		(t (mapcar #'(lambda(x)(subs x (+ 1 n ))) l))
	)
)


(defun minim(l)
	(cond
		((null l) nil)
		((atom l) l)
		(t (apply `min (mapcar 'minim l)))
	)
)


(defun nrsub (l k)
	(cond 
		((atom l) 0)
		;((and (equal (par (sum l)) 1) (equal (par k) 0)) (+ 1 (apply '+ (mapcar #'(lambda(x)(nrsub x k)) l))))
		((and (equal (par (sum l)) 1) (equal (par k) 0))(cons l (mapcar #'(lambda(x)(nrsub x k)) l)))
		;(t (apply '+ (mapcar #'(lambda(x)(nrsub x (+ 1 k))) l)))
		(t(mapcar #'(lambda(x)(nrsub x (+ 1 k))) l))
	)
)




(defun subs2(l n)
	(cond 
		((atom l) 0)
		((and (= (mod (minim l) 2) 0) (= (mod n 2)1 ) ) (+ 1 (apply '+ (mapcar #'(lambda (x) (subs2 x n)) l))))
		(t (apply '+ (mapcar #'(lambda(x)(subs2 x (+ 1 n ))) l)))
	)
)


(defun minim(l)
	(cond
		((null l) nil)
		((atom l) l)
		(t (apply `min (mapcar 'minim l)))
	)
)

;NR DE SUBLISTE CARE AU ULTIMUL ATOM NUMARIC (DE PE ORICE NIVEL) NUMAR IMPAR

;TRANSFORMA LISTA NELINIARA IN LISTA LINIARA
(DEFUN transform(l)
(COND
((null l) nil)
((numberp (car l)) (cons (car l) (transform (cdr l))))
((atom (car l)) (transform (cdr l)))
(t (append (transform (car l)) (transform (cdr l))))
)
)

;ULTIMUL NUMAR DINTR-O LISTA LINIARA
(defun ultim(l)
	(cond
		((null l) nil)
		((null (cdr l)) (car l))
		(t (ultim (cdr l)))
	)
)

;ULTIMUL NUMAR DINTR-O LISTA NELINIARA
(defun ultimNumeric (l)
	(ultim (transform l))
)

;IMPAR
(defun impar(e)
	(not (= (mod e 2) 0))
)

;FUNCTIA CU MAPCAR 
(defun numaraUltimImpar(l)
	(cond
		((null l) 0)
		((atom l) 0)
		((and (list l) (impar (ultimNumeric l)))  (+ 1 (apply '+ (mapcar #'numaraUltimImpar l))))
		(t (apply '+ (mapcar #'numaraUltimImpar l)))      
	)
)

;FUNCTIA CU MAPCAR 
(defun par(e)
	(= (mod e 2) 0)
)
(defun numaraUltimImparV2(l n)
	(cond
		((null l) 0)
		((atom l) 0)
		((and (and (list l) (impar (ultimNumeric l)))(par n))  (+ 1 (apply '+ (mapcar #'(lambda(x) (numaraUltimImparV2 x n)) l))))
		(t (apply '+ (mapcar #'(lambda(x) (numaraUltimImparV2 x (+ 1 n))) l)))      
	)
)

;sterge element E din lita neliniara

(defun stergeNeliniar(l e)
	(cond
		((null l) nil)
		((and (atom (car l)) (= (car l ) e)) (stergeNeliniar (cdr l) e))
		((and (atom (car l)) (not (= (car l ) e))) (cons (car l) (stergeNeliniar (cdr l) e)))
		(t (cons (stergeNeliniar (car l) e) (stergeNeliniar (cdr l) e) ) )
	)
)

(defun stergeLiniar(l e)
	(cond 
		((null l) nil)
		((= (car l) e) (stergeNeliniar (cdr l ) e))
		(t (cons (car l) (stergeNeliniar (cdr l) e)))
	)
)


;atomi numerici de pe nivelele impare

(defun ani(l n)
	(cond
		((null l) nil)
		((and(numberp l) (not (= (mod n 2) 0)))  l)
		((and(numberp l) (= (mod n 2) 0))  0)
		((atom l) 0)
		(t (apply '+(mapcar (lambda(x) (ani x (+ 1 n ))) l)))
	)
)

(defun mainP(l n)
	(cond 
		((null l) nil)
		((atom l) 0)
		((par (ani l 0)) (+ 1 (apply '+(mapcar #'(lambda (x) (mainP x n)) l))))
		(t (apply '+(mapcar #'(lambda(x) (mainP x (+ 1 n) )) l)))


	)
)


(defun cautaNar(l e)
	(cond 
		((null l) nil)
		((and (atom l) (= l e)) (list 1))
		((and (atom l) (not (= l e))) (list 0))
		(t (mapcan #'(lambda (x) (cautaNar x e)) l))
	)
)



(defun cautaNar2(l e)
	( apply '+ (cautaNar l e))
)


;nr par de nivele

;(defu nrNivele)


;inlocuire nr par cu succesor


(defun parSuc(l)
	(cond
	((null l) nil)
	((and (numberp (car l)) (par (car l)) (cons (+ 1 (car l)) (parSuc (cdr l)))))
	((and (numberp (car l)) (impar (car l)) (cons (car l) (parSuc (cdr l)))))
	(t (cons (parSuc (car l)) (parSuc (cdr l)))
		))
)

(defun parSuc2(l)
	(cond 
		((null l) nil)
		((and (numberp l) (par l)) (+ 1 l))
		((atom l) l)
		(t (mapcar #'parSuc2 l))
	)
)

;nr nivele

(defun nrnivel(l)
	(cond
		((null l) 0)
		((atom l) 0)
		(t (+ 1 (apply 'max (mapcar 'nrnivel l))))
	)
)

(defun main99(l)
	(cond 
		((null l) 0)
		((atom l) 0)
		((and (list l) (par (nrnivel l))) (+ 1 (apply '+(mapcar 'main99 l))))
		(t (apply '+ (mapcar 'main99 l)))
	)
)


(defun inlocuire(l n k e) 

	(cond
		((null l ) nil)
		((and (atom l) (= k n)) e)
		((and (atom l) (not (= k n))) l)
		(t (mapcar #'(lambda(x) (inlocuire x (+ 1 n) k e)) l))
	)
)

;nr d eliste pentru care nr maxim e par

(defun maximFinal(l)
	(cond 
		((null l ) nill)
		((numberp l) l)
		((atom l) -999)
		(t (apply #'max(mapcar #'maximFinal l)))
	)
) 

(defun numaraFinal(l)
	(cond
		((null l) nil)
		((atom l) 0)
		((par (maximFinal l )) (+ 1 (apply '+(mapcar 'numaraFinal l ))))
		(t (apply '+(mapcar 'numaraFinal l)))
	)
)


