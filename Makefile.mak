# Set the compiler and compiler flags
JAVAC = javac
JAVAC_FLAGS = -d bin -cp bin

# Set the classpath and main class
CLASSPATH = bin
MAIN_CLASS = SimPlicity

# Set the source and output directories
SRC_DIR = src
OUT_DIR = bin

# Define the targets and dependencies
all: $(OUT_DIR)/$(MAIN_CLASS).class

$(OUT_DIR)/$(MAIN_CLASS).class: $(wildcard $(SRC_DIR)/*.java)
	$(JAVAC) $(JAVAC_FLAGS) $^

# Define the run target
run: $(OUT_DIR)/$(MAIN_CLASS).class
	java -cp $(CLASSPATH) $(MAIN_CLASS)

# Define the clean target
clean:
	rm -rf $(OUT_DIR)/*.class