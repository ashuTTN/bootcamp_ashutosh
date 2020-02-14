function ques_1(){ 

    var amount = prompt("Enter Amount");
    var interest = prompt("Enter Interest Rate");
    var yrs = prompt("Enter Number Of Years");

    var total = (amount * interest * yrs)/100;
    window.alert("Simple Interest = " + total);
}

function ques_2(){
    var word = prompt("Enter Your String");
    var flag = 0 ;
    for(var i = 0; i < word.length/2; i++){
        if(word[i] != word[word.length - 1 - i ]){
            flag = 1;
        }
    }
    if(flag == 1){
        window.alert("NOT PALINDROME");
    }
    else{
        window.alert("PALINDROME")
    }
}

function ques_3(){
    var radius = prompt("Enter Circle Radius");
    var area = 3.14*radius*radius;
    window.alert("Area = " + area); 
}


function ques_4(){
    var employee={name : "Ashutosh", designation: "Trainee", competency: "Android"};
    console.log("Original object: ");
    console.log(employee);
    var objCopy=Object.assign({},employee);
    console.log("Copied object: ");
    console.log(objCopy);

}
function ques_5(){
    console.log("Create a list of objects of Employee with info as follow : Name, age, salary ,DOB");
    var list = [
        {name : 'Ashutosh' , age: '20' , salary: 15000,dob: '10-03-99'},
        {name : 'Anupam' , age: '21' , salary: 10,dob: '10-04-98'},
        {name : 'Subarno' , age: '22' , salary: 782,dob: '12-03-97'},
        {name : 'Lakshay' , age: '20' , salary: 200,dob: '12-04-98'},
        {name : 'aayush' , age: '20' , salary: 7500,dob: '17-07-99'},
        {name : 'chirag' , age: '20' , salary: 6000,dob: '21-03-95'},
        {name : 'ashish' , age: '20' , salary: 20001,dob: '10-05-99'}
    ];
    for (var i = 0 ;i<list.length;i++){
            console.log(list[i]);
    }
    
    console.log("Filter all employees with salary greater than 5000");
    for (var i = 0 ;i<list.length;i++){
        if(list[i].salary>5000){
            console.log(list[i]);
        }
    }

    console.log("Group employees on the bassis of their age");
    res = {}
    list.forEach(myfunction);
    function myfunction(item){
        if(!res.hasOwnProperty(item.age)){
            res[item.age]=[];
        }
        res[item.age].push(item);
    }
    console.log(res);
    console.log(JSON.stringify(res));
    


    console.log("Fetch employees with salary less than 1000 and age greater than 20. Then give them an increment 5 times their salary.")
    for(var i = 0 ;i<list.length;i++){
        if(list[i].salary > 20 && list[i].salary < 5000) {
            console.log(list[i]);
            list[i].salary = list[i].salary+list[i].salary*5; 
            console.log("Incrementing salary");
            console.log(list[i].salary);
        }
    }
}
