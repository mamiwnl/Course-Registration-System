from abc import ABC, abstractmethod

class User(ABC):
    def __init__(self, id, password, name, surname):
        self.__id = id
        self.__password = password
        self.__name = name
        self.__surname = surname

    @abstractmethod
    def getInfo(self):
        pass

    def getId(self):
        return self.__id

    def setId(self, id):
        self.__id = id

    def getPassword(self):
        return self.__password

    def setPassword(self, password):
        self.__password = password

    def getName(self):
        return self.__name

    def setName(self, name):
        self.__name = name

    def getSurname(self):
        return self.__surname

    def setSurname(self, surname):
        self.__surname = surname
