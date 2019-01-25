%{
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

extern int line;

void yyerror(const char *str) 
{
    printf("Syntax error at line: %d %s \n", line,  str);
    exit(1);
}

int yywrap()
{
    return 1;
} 

int main(int argc, char *argv[]) {

    main_flex(argc, argv);
    yyparse();
    printf("All good.\n");
    return 0;
}

%}

%token IDENT CONST CONSTREAL INT FLOAT ARRAY BOOL CHAR
%token START IF ELSE WHILE INPUT PRINT 
%token SEMICOLON OPAR CPAR OBRACE CBRACE LENGTH
%token PLUS MINUS MULT DIV MOD GT LT GE LE EQ NE ASSIGN

%%

program: START compound_stmt;
compound_stmt: OBRACE stmt_list CBRACE;
stmt_list: stmt | stmt_list stmt;
stmt: decl SEMICOLON | arraydec | assign SEMICOLON | loop | if_stst | iostmt SEMICOLON;
decl: type IDENT;
type: INT | FLOAT | BOOL | CHAR;
arraydecconst: CONST | IDENT;
arraydec: ARRAY type OPAR arraydecconst CPAR IDENT SEMICOLON;
arrayindex: IDENT | CONST | expr;
arrayacces: IDENT OPAR arrayindex CPAR;
array_length: LENGTH OPAR IDENT CPAR;
assignmember: arrayacces | IDENT;
assign: assignmember ASSIGN expr | assignmember ASSIGN INPUT | decl ASSIGN expr | decl ASSIGN INPUT;
op: PLUS | MINUS | MULT | DIV | MOD;
constant: CONST | CONSTREAL;
expr: assignmember | constant | expr op assignmember | expr op constant | OPAR expr CPAR;
loop: WHILE OPAR condition CPAR compound_stmt;
condition: expr rel_op expr;
rel_op: LT | LE | GT | GE | EQ | NE;
if_stst: IF OPAR condition CPAR compound_stmt 
       | IF OPAR condition CPAR compound_stmt ELSE compound_stmt;
iostmt: INPUT | output; 
output: PRINT IDENT | PRINT constant | PRINT expr;


%%