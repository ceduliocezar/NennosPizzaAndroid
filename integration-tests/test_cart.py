import os
import unittest
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

from appium import webdriver

# Returns abs path relative to this file and not cwd
PATH = lambda p: os.path.abspath(
    os.path.join(os.path.dirname(__file__), p)
)


class PizzaListTests(unittest.TestCase):
    def setUp(self):
        desired_caps = {
            'platformName': 'Android',
            'deviceName': 'Android Emulator',
            'app': PATH('../app/build/outputs/apk/debug/app-debug.apk')
        }

        self.driver = webdriver.Remote('http://localhost:4723/wd/hub', desired_caps)

    def tearDown(self):
        self.driver.quit()

    def test_add_pizza_to_cart(self):
        self.waitUntilId("pizza_name_tv")
        self.driver.find_element_by_xpath("//android.widget.TextView[@text='Margherita']")
        self.driver.find_element_by_xpath("//android.widget.TextView[@text='$6']").click()

        self.driver.find_element_by_id("tool_bar_cart_image").click()

        self.driver.find_element_by_xpath("//android.widget.TextView[@text='Margherita']")
        self.driver.find_element_by_id("cart_item_delete_iv")

    def test_add_drink_to_cart(self):
        self.driver.find_element_by_id("tool_bar_cart_image").click()
        self.driver.find_element_by_id("tool_bar_drinks").click()

        self.waitUntilId("drink_name_tv")
        self.driver.find_element_by_id("drink_add_iv").click()

        self.driver.back()
        self.driver.back()

        self.driver.find_element_by_id("tool_bar_cart_image").click()

        self.waitUntilId("cart_item_title_tv")

        self.driver.find_element_by_xpath("//android.widget.Button[@text='Checkout ($1)']")
        self.driver.find_element_by_xpath("//android.widget.TextView[@text='$1']")

    def test_empty_cart(self):
        self.driver.find_element_by_id("tool_bar_cart_image").click()
        self.driver.find_element_by_id("tool_bar_drinks").click()

        self.waitUntilId("drink_name_tv")
        self.driver.find_element_by_id("drink_add_iv").click()

        self.driver.back()
        self.driver.back()

        self.driver.find_element_by_id("tool_bar_cart_image").click()

        self.waitUntilId("cart_item_title_tv")

        self.driver.find_element_by_xpath("//android.widget.Button[@text='Checkout ($1)']")
        self.driver.find_element_by_xpath("//android.widget.TextView[@text='$1']")

    def test_empty_car(self):
        self.driver.find_element_by_id("tool_bar_cart_image").click()
        self.driver.find_element_by_id("empty_cart_tv")

    def waitUntilId(self, element_id):
        WebDriverWait(self.driver, 20).until(
            EC.presence_of_element_located((By.ID, element_id))
        )


if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(PizzaListTests)
    unittest.TextTestRunner(verbosity=2).run(suite)
