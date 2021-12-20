package authorization;

public class Registr {
    private String name;
    private String login;
    private String password;
    private String email;
    private String question;
    private int age;

    public Registr(String name, String login, String password, int age, String email, String question) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.age = age;
        this.question = question;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getLogin() {return login;}
    public void setLogin(String login) {this.login = login;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getQuestion() {return question;}
    public void setQuestion(String question) {this.question = question;}

    @Override
    public String toString() {
        return "name=" + name + ", login=" + login + ", password=" + password + ", email=" + email + ", age=" + age + ", question=" + question;
    }
}