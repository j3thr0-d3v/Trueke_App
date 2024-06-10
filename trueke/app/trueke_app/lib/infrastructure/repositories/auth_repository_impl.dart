import 'package:trueke_app/domain/domain.dart';
import 'package:trueke_app/infrastructure/datasource/auth_datasource_impl.dart';

class AuthRepositoryImpl extends AuthRepository{

  final AuthDatasource authDatasource;

  AuthRepositoryImpl({AuthDatasource? authDatasource}): authDatasource = authDatasource ?? AuthDatasourceImpl();

  @override
  Future<User> login(String username, String password) {
   
    return authDatasource.login(username, password);

  }

  @override
  Future<User> register() {
   return authDatasource.register();
  }
  
  @override
  Future<User> checkAuthStatus(String token) {
    return authDatasource.checkAuthStatus(token);

  }
  
}