//������������ ����������
//3212015051

#include <stdio.h>

int main()
{
    int i,n,all=0; //n= ����� ��� �����������,all -> ����
    char answer;
    char a,b; //���� a ��� b ����� �a ������ ��� �������� ��� �� �������� ��� ����������.
    float m1,m2,m3,m4,m5,m6,lim,max,min,f_mark,per; /* ���� m* ����� �� ������ ��� ������,max ��� min � �������� ��� � ��������� ������ ����������,f_mark � ������� ������ ��� �������������� ��� lim �� ���� ��� ����� �������� �� ������.per ����� �� ������� */
    n=0; //����������� ��� ������� ���.
    printf("Give the limit!\n");
    scanf("%f", &lim);
    do{
        all++;
         printf("Give the marks of 6 judges and the first letters from his name and his surname\n");
        scanf("%f\n %f\n %f\n %f\n %f\n %f\n %c\n %c",&m1,&m2,&m3,&m4,&m5,&m6,&a,&b);
        {
        max=m1;              //������ ��������
        if (m2>max)
        max=m2;
        else if (m3>max)
        max=m3;
        else if (m4>max)
        max=m4;
        else if (m5>max)
        max=m5;
        else if (m6>max)
        max=m6;
        }

        {
        min=m1;             //������ ���������
        if (m2<min)
        min=m2;
        else if (m3<min)
        min=m3;
        else if (m4<min)
        min=m4;
        else if (m5<min)
        min=m5;
        else if (m6<min)
        min=m6;
        }
       f_mark = (m1 + m2 + m3 + m4 + m5 + m6 -min - max) /4 ; //����������� ����� ���� ��� ��������������


        if (f_mark >= lim) {                                 //������� ��������� � ��������� ��� �������� ���������� ���������
        n=n+1;
        printf("The contestant with name %c %c proceeds to next round\n",a,b);
        }
        else {
            printf("The contestant with name %c %c failed\n",a,b);
        }
        printf("Type 'y'to continue or exit\n");
        scanf("%s",&answer);
    }while (answer == 'y');



    per = ((float)(all-n)/all*100); //����������� ��������
    printf("Contestants who proceed to next round are %d\n",n);
    printf("Contestants who failed are the %.2f %% \n",per);
    return 0;
    }

