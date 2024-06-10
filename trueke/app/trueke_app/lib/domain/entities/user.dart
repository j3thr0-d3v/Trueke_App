class User {
  String id;
  String username;
  String name;
  String surname;
  String roles;
  int age;
  DateTime createdAt;
  String token;
  String refreshToken;

  User({
    required this.id,
    required this.username,
    required this.name,
    required this.surname,
    required this.roles,
    required this.age,
    required this.createdAt,
    required this.token,
    required this.refreshToken,
  });
}
