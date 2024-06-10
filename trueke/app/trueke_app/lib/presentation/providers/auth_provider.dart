import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:trueke_app/domain/domain.dart';
import 'package:trueke_app/infrastructure/error/authentication_errors.dart';
import 'package:trueke_app/infrastructure/infrastructure.dart';
import 'package:trueke_app/infrastructure/shared/key_value_storage_service.dart';
import 'package:trueke_app/infrastructure/shared/key_value_storage_service_impl.dart';

final authProvider = StateNotifierProvider<AuthNotifier, AuthState>((ref) {
  final authRepository = AuthRepositoryImpl();
  final keyValueStorageService = KeyValueStorageServiceImpl();

  return AuthNotifier(
    authRepository: authRepository,
    keyValueStorageService: keyValueStorageService,
  );
});

class AuthNotifier extends StateNotifier<AuthState> {
  final AuthRepository authRepository;
  final KeyValueStorageService keyValueStorageService;

  AuthNotifier({
    required this.authRepository,
    required this.keyValueStorageService,
  }) : super(AuthState()) {
    checkAuthStatus();
  }

  Future<void> loginUser(String email, String password) async {
    await Future.delayed(const Duration(milliseconds: 500));
    try {
      final user = await authRepository.login(email, password);
      _setLoggedUser(user);
    } on CustomError catch (e) {
      logOut(e.message);
    } catch (e) {
      logOut('Something really bad happened');
    }
  }

  // Future<void> registerUser(
  //     String email, String password, String fullname) async {
  //   await Future.delayed(const Duration(milliseconds: 500));
  //   try {
  //     final user = await authRepository.register(email, password, fullname);
  //     _setLoggedUser(user);
  //   } on CustomError catch (e) {
  //     notRegistered(e.message);
  //   } catch (e) {
  //     notRegistered('Unknown error');
  //   }
  // }

  void checkAuthStatus() async {
    final token = await keyValueStorageService.getValue<String>('token');

    if (token == null) return logOut();
    try {
      final user = await authRepository.checkAuthStatus(token);
      _setLoggedUser(user);
    } catch (e) {
      logOut();
    }
  }

  void _setLoggedUser(User user) async {
    await keyValueStorageService.setKeyValue('token', user.token);

    state = state.copyWith(
      user: user,
      authStatus: AuthStatus.authenticated,
      errorMessage: '',
    );
  }

  Future<void> notRegistered([String? errorMessage]) async {
    state = state.copyWith(
      authStatus: AuthStatus.notAuthenticated,
      user: null,
      errorMessage: errorMessage,
    );
  }

  Future<void> logOut([String? errorMessage]) async {
    await keyValueStorageService.removeKey('token');

    state = state.copyWith(
      authStatus: AuthStatus.notAuthenticated,
      user: null,
      errorMessage: errorMessage,
    );
  }
}

enum AuthStatus { checking, authenticated, notAuthenticated }

class AuthState {
  final AuthStatus authStatus;
  final User? user;
  final String errorMessage;

  AuthState({
    this.authStatus = AuthStatus.checking,
    this.user,
    this.errorMessage = '',
  });

  AuthState copyWith({
    AuthStatus? authStatus,
    User? user,
    String? errorMessage,
  }) =>
      AuthState(
        authStatus: authStatus ?? this.authStatus,
        user: user ?? this.user,
        errorMessage: errorMessage ?? this.errorMessage,
      );
}
