function [ x ] = fadein( x )

for j = 1:2
    counter=900000;
    for i = 1:900000
        x(i,j) = x(i,j) * (counter/900000); %�������� ������ ��� �������
        counter=counter+1;                  %���� ������� ��� ������� 
    end                                     %initial_value=1 , final_value=2
end

end

