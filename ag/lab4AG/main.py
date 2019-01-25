

import copy

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
        raise ValueError ("nu poate fi un numar negativ sau 0")
            
            
def validatorDate(n):
    '''
    functie de validare, are rolul de a valida inputul utilizatorului, astfel in cat in matrice de adiacenta se pot introduce doar valori de "1 si 0"
    Daca se vor introduce alte valori se ridica o exceptie
    Date de intrare: n - numar natural
    Date de iesire: ValueError daca n diferit de 1 si 0 
    '''
    

    if (n != 0 and n != 1 ):
        raise ValueError("Valorile introduse pot fi doar 1 si 0 ")
    
def citireNumarVarfuri():
    '''
    Functie care citeste numarul de varfuri din graf
    Ridica exceptie daca numarul este negativ
    Date de intrare: ---
    Date de iesire: n - numarul de varfuri
    '''
    
    while(True):
        try:
            
            n=citesteInt("introdu numarul de noduri:")
            validatorNr(n)
            break
        except ValueError as ex:
            print(ex)
            
    return n


def citireAdiacenta(n):
    '''
    Functie care genereaza in memorie si returneaza o lista de liste fiind echivalentul unei matrici de adiacenta
    Lista 0 din lista "mama" va fi echivalentul liniei nr 1 din matricea de adiacenta si tot asa
    Date de intrare: n - int numarul de varfuri
    Date de iesire: matriceAdiacenta - lista de liste reprezentand o matrice de adiacenta
    '''

   
   
    matriceAdiacenta = [[0]*n for i in range(n)]
    
    for i in range (n):
        for j in range (n):
            while(True):
                if(i==j):
                    matriceAdiacenta[i][j]=0
                    break
                try:
                    print("introduceti elementul de pe linia ",i+1," si coloana ",j+1," a matricei de adiacenta: (pot fi doar valori de 0 si 1)")
                    matriceAdiacenta[i][j]=citesteInt("")
                    validatorDate(matriceAdiacenta[i][j])
                    break
                except ValueError as ex:
                    print(ex)
                    
    return matriceAdiacenta




def consistent(x,DIM,matAdi):
    
    if len(x) <= DIM: return True
    else: return False


def solution(x,DIM,matAdi):
    
    
    cond=False
    
    if len(x)==1:
        return True
    
    if sorted(x) != x:
        return False

    seen = []
    for nr in x :
        if nr in seen:
            return False
        else:
            seen.append(nr)
            
    for i in range(len(x)):
        for j in range (len(x)):
            if (matAdi[x[i]][x[j]]==1):
                cond=True;
            
    if cond == True:
        return False
    else:
        return True
    

def backIter(x,DIM,matAdi):
    sol=[]
    x=[-1] #candidate solution
    while len(x)>0:
            choosed = False
            while not choosed and x[-1]<DIM-1:
                x[-1] = x[-1]+1 #increase the last component
                choosed = consistent(x,DIM,matAdi)
            if choosed:
                if solution(x,DIM,matAdi):

                    #x=sorted(x)
                    
                    sol.append(copy.deepcopy(x))
                        

                x.append(-1) # expand candidate solution
            else:
                x = x[:-1] #go back one component
                
    
    return sol


matAdi=[[0,1,0,0,0,1,0],
        [0,0,0,0,0,0,1],
        [0,1,0,1,0,0,0],
        [0,0,0,0,0,0,1],
        [0,0,0,1,0,1,0],
        [0,0,0,0,0,0,1],
        [1,0,1,0,1,0,0]]

sol=backIter([],7,matAdi)
def prelucrare(solutie):
    '''
    functie care pastreaza doar multimile maximale din toate multimile stabile interne
    '''
    #print(sol) -> aici sunt toate solutiile stabile cu duplicate
    lenMax=0
    nr=0
    for list in sol:
        if len(list)>lenMax:
            lenMax=len(list)
    for list in sol:
        if len(list)!=lenMax:
            nr=nr+1
    
    while(nr):
        for list in sol:
            if len(list)!=lenMax:
                sol.remove(list)
                nr=nr-1
                
    #print(sol) -> aici is toate solutiile stabile maximale cu duplicate
    for list in sol:
        sorted(list)
    print(sol)
    
    
    for list in sol:
        for i in list:
            print(i+1)
        print("----")
    
    
#decomentati pentru a folosi algoritmul pentru orice graf
#n = citireNumarVarfuri()
#adi = citireAdiacenta(n)
sol2=backIter([],7,matAdi)
prelucrare(sol2)