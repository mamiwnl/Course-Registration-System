from Staff import Staff

class Lecturer(Staff):
    def __init__(self, id, password, name, surname, title):
        super().__init__(id, password, name, surname)
        self.__title = title

    # Getters and Setters for attributes
    def getTitle(self):
        return self.__title

    def setTitle(self, title):
        self.__title = title

    def getInfo(self):
        # Implementation for viewing general lecturer information
       return f"Lecturer: {self.getTitle()} {self.getName()} {self.getSurname()}"
