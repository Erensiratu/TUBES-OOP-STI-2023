#include <stdlib.h>

int main() {
    // Compile 
    system("javac -d bin -cp bin src/*.java");

    // Run 
    system("java -cp bin SimPlicity");

    // Pause
    system("pause");

    return 0;
}