function [ decoded ] = decoder( encoded,dimen )

%������������ ������ ����������������, indexes
decoded = zeros(dimen,dimen);
[~,columns] = size(encoded);
i=1;
j=1;
k=1;

    while 1
        %������� ��� ������ �������� -1
        if encoded(i+2)== -1
            %���� ��� ��� ������ ������ ���� encoded ,���� �����
            for l = k:k+encoded(i) - 1
                decoded(j,l) = encoded(i+1);
            end
            %������� ��� ������ ������� ��� ��������������� indexes
            if l==dimen
                k=1;
                j=j+1;
            else
                k=k+encoded(i);
            end
            i=i+3;
           %�� ��� ������ -1 ��� +2 ����
        else
            %������ �� ���������� �� �������� ������
            %��� ��������������� �� ���� ���� indexes
            if k==dimen
                 decoded(j,k) = encoded(i);
                k=1;
                j=j+1;
               
                i=i+1;
            else
                %������ ���� �������� �� ��������
                %��� ������� ���� indexes
                decoded(j,k) = encoded(i);
                 i=i+1;
                 k=k + 1;
            end
            
        end
        %������� ��� �� �� ������ � encoded ��� �����
        if i + 2 == columns
            break;
        end
    
    end
    %��������� ���������� ��� �� ��������� 3 �����
   if encoded(i+2) == -1
       for l = k : k + encoded(i) - 1
           decoded(j,l) = encoded(i+1);
       end
   else
       decoded(j,k) = encoded(i);
       k = k + 1;
       i = i + 1;
       decoded(j,k) = encoded(i);
       k = k + 1;
       i = i + 1;
       decoded(j,k) = encoded(i);
   end

end

