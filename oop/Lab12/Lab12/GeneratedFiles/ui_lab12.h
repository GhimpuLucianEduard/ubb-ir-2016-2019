/********************************************************************************
** Form generated from reading UI file 'lab12.ui'
**
** Created by: Qt User Interface Compiler version 5.8.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_LAB12_H
#define UI_LAB12_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_Lab12Class
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *Lab12Class)
    {
        if (Lab12Class->objectName().isEmpty())
            Lab12Class->setObjectName(QStringLiteral("Lab12Class"));
        Lab12Class->resize(600, 400);
        menuBar = new QMenuBar(Lab12Class);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        Lab12Class->setMenuBar(menuBar);
        mainToolBar = new QToolBar(Lab12Class);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        Lab12Class->addToolBar(mainToolBar);
        centralWidget = new QWidget(Lab12Class);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        Lab12Class->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(Lab12Class);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        Lab12Class->setStatusBar(statusBar);

        retranslateUi(Lab12Class);

        QMetaObject::connectSlotsByName(Lab12Class);
    } // setupUi

    void retranslateUi(QMainWindow *Lab12Class)
    {
        Lab12Class->setWindowTitle(QApplication::translate("Lab12Class", "Lab12", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class Lab12Class: public Ui_Lab12Class {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_LAB12_H
