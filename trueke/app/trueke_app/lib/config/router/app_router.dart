import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';
import 'package:trueke_app/config/router/app_router_modifier.dart';
import 'package:trueke_app/presentation/providers/auth_provider.dart';
import 'package:trueke_app/presentation/screens/check_auth_status_screen.dart';

import '../../presentation/screens/login_screen.dart';

// import 'app_router_notifier.dart';

final goRouterProvider = Provider((ref) {
  final goRouterNotifier = ref.read(goRouterNotifierProvider);

  return GoRouter(
    initialLocation: '/splash',
    refreshListenable: goRouterNotifier,
    routes: [
      ///* Auth Routes
      GoRoute(
        path: '/splash',
        builder: (context, state) => const CheckAuthStatusScreen(),
      ),
      GoRoute(
        path: '/login',
        builder: (context, state) => const LoginScreen(),
      ),
      // GoRoute(
      //   path: '/register',
      //   builder: (context, state) => const RegisterScreen(),
      // ),

      ///* Events Routes
      // GoRoute(
      //   path: '/',
      //   builder: (context, state) => const EventsScreen(),
      // ),
    ],
    redirect: (context, state) {
      final isGoingTo = state.subloc;
      final authStatus = goRouterNotifier.authStatus;

      if (isGoingTo == '/splash' && authStatus == AuthStatus.checking) {
        return null;
      }
      if (authStatus == AuthStatus.notAuthenticated) {
        if (isGoingTo == '/login' || isGoingTo == '/register') return null;

        return '/login';
      }
      if (authStatus == AuthStatus.authenticated) {
        if (isGoingTo == '/login' ||
            isGoingTo == '/register' ||
            isGoingTo == '/splash') return '/';
      }

      //Si hubiese admin o algo parecido podriamos gestionarlo aquí

      return null;
    },
  );
});
