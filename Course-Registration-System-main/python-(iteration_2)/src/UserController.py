from abc import ABC, abstractmethod

class UserController(ABC):

    @abstractmethod
    def showOptions(self):
        pass
