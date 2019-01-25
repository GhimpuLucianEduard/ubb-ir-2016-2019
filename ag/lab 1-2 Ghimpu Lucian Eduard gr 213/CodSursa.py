'''
Created on Mar 7, 2017

@author: Deus
'''

def citesteInt(msg):
    '''
    Functie care ajutala validarea numerelor intregi
    Functie ridica exceptie daca numarul riparit nu este integ, sau returneaza numarul in caz contrat
    Date de intrare: msg - un string cu un mesaj pentru a citi numarul
    Date de iesire: mesaj de eroare daca n nu est eintreg sau n daca n este intreg
    '''
    while(True):
        try:
            n=int(input((msg)))
            break
        except ValueError:
            print("Trebuei sa introduceti un numar valid")
            
    return n
   
def validatorNr(nr):
    '''
    Functie care ajuta la validarea numarului de noduri
    Numarul de noduri poate fi strict pozitiv
    Date de intrare:  nr - int nr de noduri
    Date de iesire: mesaj de eroare daca n<=0
    '''
    if nr<=0:
        raise ValueError ("nr de noduri nu poate fi un numar negativ")
            
            
def validatorDate(n):
    '''
    functie de validare, are rolul de a valida inputul utilizatorului, astfel in cat in matrice de adiacenta se pot introduce doar valori de "1 si 0"
    Daca se vor introduce alte valori se ridica o exceptie
    Date de intrare: n - numar natural
    Date de iesire: ValueError daca n diferit de 1 si 0 
    '''
    

    if (n != 0 and n != 1 ):
        raise ValueError("Valorile introduse pot fi doar 1 si 0 ")
    
    
def nrArce(matriceAdiacenta):
    '''
    functie care primeste ca parametru o matrice de adiacenta si returneaza numarul de arce din graf
    date de intrare: matriceAdiacenta - lista de liste
    date de iesire: nrArce - int
    '''
    
    nrArce=0
    
    for i in range (len(matriceAdiacenta)):
        for j in range (len(matriceAdiacenta)):
            if matriceAdiacenta[i][j]==1:
                nrArce+=1
                
    return nrArce
            
            
def citireAdiacenta():
    '''
    Functie care genereaza in memorie si returneaza o lista de liste fiind echivalentul unei matrici de adiacenta
    Lista 0 din lista "mama" va fi echivalentul liniei nr 1 din matricea de adiacenta si tot asa
    Date de intrare: ---
    Date de iesire: matriceAdiacenta - lista de liste reprezentand o matrice de adiacenta
    '''

    ladi=[[]]
    
    while(True):
        try:
            
            n=citesteInt("introdu numarul de noduri:")
            validatorNr(n)
            break
        except ValueError as ex:
            print(ex)
   
   
    matriceAdiacenta = [[0]*n for i in range(n)]
    
    for i in range (n):
        for j in range (n):
            while(True):
                try:
                    print("introduceti elementul de pe linia ",i+1," si coloana ",j+1," a matricei de adiacenta: (pot fi doar valori de 0 si 1)")
                    matriceAdiacenta[i][j]=citesteInt("")
                    validatorDate(matriceAdiacenta[i][j])
                    break
                except ValueError as ex:
                    print(ex)
                    
    return matriceAdiacenta
            
    
def construireIncidenta(matriceAdiacenta,nrArce):
    '''
    functie care primeste ca paramentru o lista de liste, reprezentand matrice de adiacenta a unui graf si numarul de arce al grafului
    returneaza o lista de liste fiind echivalentul matricii de incidenta
    Date de intrare: matriceAdiacenta - lista de liste, nrArce - int
    Date de iesire: matriceIncidenta - lista de liste 
    '''
    
    nr=0
    matriceIncidenta = [[0]*nrArce for i in range(len(matriceAdiacenta))]
    
    
    for i in range (len(matriceAdiacenta)):
        for j in range (len(matriceAdiacenta)):
            if matriceAdiacenta[i][j]==1:
                matriceIncidenta[i][nr]=1
                matriceIncidenta[j][nr]=-1
                nr+=1
                
    return matriceIncidenta

def tiparireIncidenta(matriceIncidenta):
    '''
    Functie care primeste ca parametru o lista de liste reprezentant matricea de incidenta a unui graf
    Functia printeaza lista de liste sub forma de tabel/matrice
    Date de intrare: matriceIncidenta - lista de liste
    Date de iesire: ---
    '''
    
    for linie in matriceIncidenta:
        print(linie)
    
    


def teste():
    '''
    Functie de testare
    '''

    matriceAdiacenta = [[0,0,1,0],[0,0,1,1],[0,0,0,0],[1,0,0,0]]
    
    assert(nrArce(matriceAdiacenta)==4)
    
    nr = nrArce(matriceAdiacenta)
    
    matriceIncidenta = construireIncidenta(matriceAdiacenta, nr)
    
    assert(matriceIncidenta==[[1,0,0,-1],[0,1,1,0],[-1,-1,0,0],[0,0,-1,1]])

    
    try:
        validatorDate(1)
        assert(True)
    except ValueError:
        assert(False)
        
    try:
        validatorDate(2)
        assert(False)
    except ValueError:
        assert(True)


def main():
    
    teste()    
    m=citireAdiacenta()
    nr=nrArce(m)
    tiparireIncidenta(construireIncidenta(m,nr))

main()




