import 'package:trueke_app/config/constants/environment.dart';
import 'package:trueke_app/domain/datasources/auth_datasource.dart';
import 'package:trueke_app/domain/entities/user.dart';
import 'package:dio/dio.dart';
import 'package:trueke_app/infrastructure/error/authentication_errors.dart';
import 'package:trueke_app/infrastructure/mappers/user_mappers.dart';

class AuthDatasourceImpl extends AuthDatasource{
  
  final dio = Dio(BaseOptions(baseUrl: Environment.apiUrl));
  
  @override
  Future<User> login(String username, String password) async {
    
    try{
      final response = await dio.post(
        '/auth/login',
        data: {'username':username, 'password':password}
        );
      final user = UserMappers.userFromJson(response.data);
      return user;
    
    }on DioException catch (e) {
      if (e.response?.statusCode == 401) {
        throw CustomError(
            message: e.response?.data['message'] ?? 'Invalid token');
      }

      if (e.type == DioExceptionType.connectionTimeout) {
        throw CustomError(message: 'Please check internet connection');
      }
      throw Exception();
    } catch (_) {
      throw Exception('Something really bad just happened');
    }
  }

  @override
  Future<User> register() {
    // TODO: implement register
    throw UnimplementedError();
  }

  @override
  Future<User> checkAuthStatus(String token) async{
    try {
      final response = await dio.get(
        options: Options(headers: {
          'Authorization': 'Bearer $token',
        }),
        '/auth/check-status',
      );

      final user = UserMappers.userFromJson(response.data);
      return user;
    } on DioException catch (e) {
      if (e.response?.statusCode == 401) {
        throw CustomError(
            message: e.response?.data['message'] ?? 'Invalid token');
      }

      if (e.type == DioExceptionType.connectionTimeout) {
        throw CustomError(message: 'Please check internet connection');
      }
      throw Exception();
    } catch (_) {
      throw Exception('Something really bad just happened');
    }
  }
  
}