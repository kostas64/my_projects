//K����������� ����������
//3212015051

#include <stdio.h>
#include <time.h>

int main()
{
   int i,random,c1,c2,n1,n2,k1,k2; //c1 �������� ����� ��� 1�� ������ ��� c2 ����������,random ����� � ������� ��� �����.n1 ������� ������� 1�� ������ ��� n2 ����������,k1 ����� � �������� ��� ������� ��� 1�� ������ �� ��� ����� ��� �� k2 ����������.
   random=rand()%101;
   c1=0;
   c2=0;

  do
  {
     do{
        printf("First player gives number 1st and second the other one:\n");
        scanf("%d\n %d", &n1,&n2);
        }while (n1<=0 && n1>100 && n2<=0 && n2>100);
        srand(time(NULL));  //������� �����
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
     random=rand()%101; //�������� ����� ����� ������ ������� �� ������ ��������.
} while (c1<5 && c2<5);
  if (c1>c2)
    printf("The winner is the 1st player!\n");
    else if (c2>c1)
    printf("The winner is the 2nd player!\n");
    return 0;
}
