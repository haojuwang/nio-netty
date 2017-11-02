namespace java com.lihao.netty.thrift2
namespace php thrift

typedef i16 short
typedef i32 int
typedef i64 long
typedef bool boolean
typedef string String


struct User{
    1: String username,
    2: int age,
    3: int id,

}

struct UserRequest{
    1:int id;
    2:String token;
    3:String username;
    4:String passworld;
}


exception DataException {
    1: String message;
    2: String callStack;
    3: String date
}

service UserService{
    User login(1:required UserRequest userRequst) throws(1: DataException dataException);
    void logOut(1:required UserRequest userRequst) throws(1: DataException dataException);
    list<User> userList()  throws(1: DataException dataException);
}


