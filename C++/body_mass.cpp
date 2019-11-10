#include <iostream>

using namespace std;

class patient //����� ��� ������ ��� ������������ �� ��� �� ���.
{
public:
float get_height() const {return height;}
int get_weight() const{return weight;}
float calc(float height,int weight){return float(weight)/float(height*height);}
patient(string name,int age,float height,int weight){this->height=height; this->weight=weight; this->age=age; this->name=name;} //Constructor

private: //�������� ��� ������.
    string name;
    int age,weight;
    float height;
};

int main()
{
    string name;
    char answer;
    int weight,age,counter=0;
    float height,BMI=0;
    do
     {
         if(counter==0)
       cout << "Welcome to the Body Mass Index calculator programme.To start,type\nyour name,age,height(in m) and your weight in kg \n";
         else
       cout << "Welcome again to the Body Mass Index calculator programme.To start,\ntype your name,age,height(in m) and your weight in kg \n";
       cin >> name >>age;
   if (age>0 && age<18)
     {
       cout << name <<",the BMI has no meaning for people aged under 18\n";
       cout << "Do you want to continue? If yes press Y,if not press N\n" ;
       cin >> answer; //�������� ��� �� �� �� ��������� ������� ��� ��� ���� ������� ������ �� ����� � �������.
          if (answer!='Y')
        return 0;
          else
           {
         cout << "Height and weight remained\n";
         counter++;
           }
     }
    cin >> height >> weight;
   patient pat(name,age,height,weight); //������������ ����������� ��� �� �����.
   height=pat.get_height(); //��������� �����
   weight=pat.get_weight();
   BMI=pat.calc(height,weight);//����������� ��� ������.
   cout <<"Your BMI is: "<<BMI <<"\n";
   cout<< "Do you want to test again? If yes press Y,if not press N\n" ;
    cin>> answer;
      }while(answer=='Y');
    return 0;
}
