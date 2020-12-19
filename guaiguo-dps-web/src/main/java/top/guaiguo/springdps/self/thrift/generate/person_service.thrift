namespace java top.guaiguo.springdps.threadpool.thrift

struct Person{
    1:i32 id,
    2:string name
}

enum PersonType{
    man = 1 ,
    woman = 2
}

service PersonService{
    Person getPerson(1:PersonType type),

}
