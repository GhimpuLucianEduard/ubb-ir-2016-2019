%{
#include <stdio.h>
#include<stdlib.h>
#include<string.h>
#include<math.h>

typedef struct
{
  int code;
  int ts_id;
  char name[250];
}Token;

typedef struct
{
  int id;
  char name[250];
}TokenTS;

int tokensTSIndex = 0;
int tokensIndex = 0;
Token tokens[500];
TokenTS tokensTS[50];

void addToken(int code, char name[], int ts_id)
{
  tokens[tokensIndex].code = code;
  tokens[tokensIndex].name = name;
  tokens[tokensIndex].ts_id = ts;
  tokensIndex++;
}

void addTokenTS(int code, char atom[], int isIdent)
{

  if (strlen(atom) > 250 && isIdent == 1) 
  {
    printf(stderr, "ERROR: Identifier %s toolong! \n", atom);
    return ;
  }

  for (int i=0; i < tokensTSIndex; ++i) 
  {
    if (strcmp(tokensTS[i].name, atom) == 0) 
    {
      int index = tokensTS[i].id;
      tokens[tokensIndex].code = code;
      tokens[tokensIndex].ts_id = index;
      tokens[tokensIndex].name = atom;
      tokensIndex++;
      return;
    }
  }
  
  tokensTS[tokensTSIndex].id = tsConstsCount;
  tokensTS[tokensTSIndex].name = atom;
  tokensTSIndex++;

  int index = tokensTS[i].id;
  addToken(code, atom, index);

}

void printTokens()
{
  printf("Tokens: \n");
  for (i = 0; i < tokensIndex; i++) 
  {
		printf("TokenCode: %d, Token: %s, TS_CODE: %d \n", tokens[i].code, tokens[i].name, tokens[i].ts_id);
	}
}

void printTokensTS(){
  printf("TS_Tokens: \n");
  for (i = 0; i < tokensTSIndex; i++) 
  {
		printf("Token: %s, TS_CODE: %d \n", tokensTS[i].name, tokensTS[i].id);
	}
}

%}

digit     [0-9]
letter  [a-zA-Z]

%%
{digit}+             addTokenTS(1, yytext, 0);
{digit}+"."{digit}*   addTokenTS(1, yytext, 0);
{letter}*     addTokenTS(0, yytext, 1);
start         addToken(2, yytext, -1);
while         addToken(3, yytext, -1);
if            addToken(4, yytext, -1);
else          addToken(5, yytext, -1);
int           addToken(6, yytext, -1);
float         addToken(7, yytext, -1);
array         addToken(8, yytext, -1);
input         addToken(9, yytext, -1);
print         addToken(10, yytext, -1);
\{             addToken(11, yytext, -1);
\}             addToken(12, yytext, -1);
\;             addToken(13, yytext, -1);
\)             addToken(14, yytext, -1);
\(             addToken(15, yytext, -1);
\+             addToken(16, yytext, -1);
\-             addToken(17, yytext, -1);
\*             addToken(18, yytext, -1);
\/             addToken(19, yytext, -1);
\%             addToken(20, yytext, -1);
\>             addToken(21, yytext, -1);
\<             addToken(22, yytext, -1);
\>=            addToken(23, yytext, -1);
\<=            addToken(24, yytext, -1);
\==            addToken(25, yytext, -1);
\!=            addToken(26, yytext, -1);
\=             addToken(27, yytext, -1);

[ \t\n]+          /* skip whitespace */

%%

int main(int argc, char *argv[]) 
{
  if (argc != 2) {
		printf("Please provide a source file!");
		return 0;
	}

  yyin = fopen(argv[1], "r");
	yylex();

  printTokens();
  printTokensTS();
}