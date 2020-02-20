package ques_4;
class Singleton
{
    private static Singleton instance = null;
    public String s;
    private Singleton()
    {
        s = "Hello, my name is Ashutosh!";
    }

    // static method to create instance of Singleton class
    public static Singleton getInstance()
    {
        if (instance == null) {
            instance = new Singleton();
        }

        return instance;
    }
}

