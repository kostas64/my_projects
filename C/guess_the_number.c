//Kωνσταντινος Ευκαρπιδης
//3212015051

#include <stdio.h>
#include <time.h>

int main()
{
   int i,random,c1,c2,n1,n2,k1,k2; //c1 μετρητης νικων του 1ου παικτη και c2 αντιστοιχα,random ειναι ο αριθμος του κριτη.n1 επιλογη αριθμου 1ου παικτη και n2 αντιστοιχα,k1 ειναι η αποσταση του αριθμου του 1ου παικτη με του κριτη και το k2 αντιστοιχα.
   random=rand()%101;
   c1=0;
   c2=0;

  do
  {
     do{
        printf("First player gives number 1st and second the other one:\n");
        scanf("%d\n %d", &n1,&n2);
        }while (n1<=0 && n1>100 && n2<=0 && n2>100);
        srand(time(NULL));  //τυχαιες τιμες
        printf("%d\n",random);
       k1=random-n1;
        if (k1<0) {
            k1=-k1; }
        k2=random-n2;
        if (k2<0)
            k2=-k2;
        if (k1>k2) {
            c2++; }
        else if (k2>k1) {
            c1++; }
     random=rand()%101; //οριζουμε μεχρι ποιον αριθμο θελουμε να πεταει τυχαιους.
} while (c1<5 && c2<5);
  if (c1>c2)
    printf("The winner is the 1st player!\n");
    else if (c2>c1)
    printf("The winner is the 2nd player!\n");
    return 0;
}
