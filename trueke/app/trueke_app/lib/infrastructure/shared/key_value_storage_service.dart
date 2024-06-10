abstract class KeyValueStorageService {
  //Uso generico T -> por ej. cuando mandas un entero lo trata como entero
  Future<void> setKeyValue<T>(String key, T value);
  Future<T?> getValue<T>(String key);
  Future<bool> removeKey(String key);
}
