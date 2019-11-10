//Κωνσταντινος Ευκαρπιδης
//3212015051


#include <stdio.h>


int main()
{
    char ch;
    int line,column,c1,c2,c3,k; //c1 παρενθεσεις,c2 εισαγωγικα,c3 παυλες
    line=1;
    column=1;
    c1=0;
    c2=0;
    c3=0;
    ch=getchar();
    while (ch!=EOF)
    {



        if (ch=='(')                     //Ελεγχος χαρακτηρων
          {
            c1++;
          }
        else if (ch==')')
        {
            c1--;
            if (c1<0)
            printf("There is a bracket which closes in line %d and column %d but it doesn't open\n",line,column);
       }

        if (ch=='<')
            c2++;
        else if (ch=='>')
           {
               c2--;
           if (c2<0)
         printf("There is a quote which closes in line %d and column %d but it doesn't open\n",line,column);
        }
        if (ch=='-')
            c3++;

        if (ch=='\n')
            {
            line++;
            column=0;
            }

     ch=getchar();
     column++;
    }
k=c3%2;        //Η πραξη αφορα τις παυλες
if (k==1) {
if (c1<0 && c2<0)
     c1 = -c1; //Απολυτη τιμη
     c2 = -c2;
  printf("%d bracket(s)\n%d quote(s)\nand 1 dash are open in your text",c1,c2); }
  else
 if (c1<0 && c2<0)
    c1 = -c1; //Απολυτη τιμη
    c2 = -c2;
   printf("%d bracket(s)\n%d quote(s)\n are open in your text",c1,c2);
    return 0;
}
