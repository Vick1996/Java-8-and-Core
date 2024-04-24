package Java8;

class Emp
{
    private String name;
    private double sal;
    private String department;
    private int age;
    private char gender;

    Emp(double sal, String name, String department,int age, char gender)
    {
        this.gender = gender;
        this.age = age;
        this.department = department;
        this.sal = sal;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "name='" + name + '\'' +
                ", sal=" + sal +
                ", department='" + department + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}