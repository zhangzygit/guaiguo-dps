package top.guaiguo.springdps.thrift.server;

import org.apache.thrift.TException;
import top.guaiguo.springdps.thrift.generate.Person;
import top.guaiguo.springdps.thrift.generate.PersonService;
import top.guaiguo.springdps.thrift.generate.PersonType;

public class PersonServiceImpl implements PersonService.Iface {

    @Override
    public Person getPerson(PersonType type) throws TException {
        Person person = new Person();
        switch (type) {
            case man:
                person.setName("man");
                break;
            case woman:
                person.setName("woman");
                break;
            default:
                person.setName("default");
                break;
        }
        return person;
    }
}
