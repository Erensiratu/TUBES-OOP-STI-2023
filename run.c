#include <stdlib.h>

int main() {
    // Compile 
    system("javac -d test -cp test src/*.java");

    // Run 
    system("java -cp test SimPlicity");

    // Pause
    system("pause");

    return 0;
}