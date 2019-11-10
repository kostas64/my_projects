function [ x ] = fadeout(x)

for j = 1:2
    counter=900000;
    for i = 1:900000
        x(i,j) = x(i,j) * (counter/900000); %Γραμμική μέιωση του πλάτους
        counter=counter-1;                  %λόγω μείωσης του μετρητή 
    end                                     %%initial_value=1 , final_value=0
end

end

