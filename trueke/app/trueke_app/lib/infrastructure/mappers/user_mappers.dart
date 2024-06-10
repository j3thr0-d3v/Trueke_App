import 'package:trueke_app/domain/entities/user.dart';

class UserMappers {
  static User userFromJson(Map<String, dynamic> json) => User(
    id: json['id'], 
    username: json['username'], 
    name: json['name'], 
    surname: json['surname'], 
    roles: json['roles'], 
    age: json['age'], 
    createdAt: DateTime.parse(json['createdAt']),
    token: json['token'], 
    refreshToken: json['refreshToken']
    );
}