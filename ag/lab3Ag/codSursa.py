'''
chestii
'''

import math
infinit = math.inf

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

def citireCosturi(matriceAdiacenta,n):
    '''
    Functie care citeste costul arcelor
    Date de intrare - n - numarul de varfuri
    Date de iesie - matriceCosturi - lista de liste cu valorile de cost de la un nod la altul
    
    DACA intre 2 varfuri nu exista arc, costul este infinit
    '''

    matriceCosturi = [[0]*n for i in range(n)]
    
    for i in range (n):
        for j in range (n):
            while(True):
                if(i==j):
                    matriceCosturi[i][j]=0
                    break
                if(matriceAdiacenta[i][j]==0):
                    break
                else:
                    try:
                        print("introduceti costul  de la varful ",i+1," la varful ",j+1)
                        matriceCosturi[i][j]=citesteInt("")
                        validatorNr(matriceCosturi[i][j])
                        break
                    except ValueError as ex:
                        print(ex)
                    
    return matriceCosturi


def citireVarfSursa():
    '''
    Functie folosita pentru citirea varfului sursa
    Date de intrare: ---
    Date de iesire: vs - int - varful sursa
    '''
    
    while(True):
        try:
            
            vs=citesteInt("varful sursa:")
            validatorNr(vs)
            break
        except ValueError as ex:
            print(ex)
            
    return vs
    

def distantaMinima(distante,parcurs,n):
    '''
    Functie care gaseste costul minim din lista distantelor care nu sunt parcurse
    Date de intrare: distante - lista de costuri a distantelor actuale de la sursa data
                     parcurs - lista de boolene (cu valoare true daca un varf a fost parcurs si false daca nu)
                     n - int - numarul de varfuri
    Date de iesire: indexMinim - int indexul minimului
    '''
    
    minim = infinit
    
    for i in range(n):
        if parcurs[i] == False and distante[i] <= minim:
            minim = distante[i]
            indexMinim = i
            
    return indexMinim


def printareSolutie(distante,  n):
    '''
    Functie care printeaza solutia finala
    Date de intrare: distante - lista cu costul final al distantelor, n - numarul de varfuri
    Date de iesire: ---    
    '''
    
    print("Varf    Distanta de la Sursa la Varf")
    for i in range (n):
        print(i+1,'        ',distante[i])
        
        
def drumMinim(matriceCosturi, varfSursa, n):
    '''
    Functie care determina drumul cel mai scurt de la un varf sursa la restul varfurilor
    Dare de intrare:  matriceCosturi - list de liste cu costuri, varfSursa - int, n - nr de varfuri int
    Date de iesire: --- (se tipareste solutia)
    '''
    
    
    distante = [infinit]*n
    
    parcurs = [False]*n
    
    distante[varfSursa-1] = 0
    
    
    for i in range (n-1):
        
        
       
        min = distantaMinima(distante, parcurs, n)
        parcurs[min] = True
        
        
        for j in range(n):
            
            if (not(parcurs[j]) and matriceCosturi[min][j] > 0 and distante[min] != infinit and distante[min] + matriceCosturi[min][j] < distante[j]):
                distante[j] = distante[min] + matriceCosturi[min][j]
                
                
              
                
 
    printareSolutie(distante, n)
    
        
    







n = citireNumarVarfuri()
adi = citireAdiacenta(n)
cost = citireCosturi(adi,n)
vs = citireVarfSursa()

drumMinim(cost, vs, n)


    
    