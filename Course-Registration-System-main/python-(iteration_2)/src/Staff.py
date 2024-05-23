from User import User

class Staff(User):
	
    def __init__(self, id, password, name, surname):
        super().__init__(id, password, name, surname)

    def getInfo(self):
      
        return f"Name: {self.getName()}\nSurname: {self.getSurname()}"
