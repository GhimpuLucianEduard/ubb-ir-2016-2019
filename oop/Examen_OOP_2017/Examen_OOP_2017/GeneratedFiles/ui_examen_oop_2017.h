/********************************************************************************
** Form generated from reading UI file 'examen_oop_2017.ui'
**
** Created by: Qt User Interface Compiler version 5.8.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_EXAMEN_OOP_2017_H
#define UI_EXAMEN_OOP_2017_H

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

class Ui_Examen_OOP_2017Class
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *Examen_OOP_2017Class)
    {
        if (Examen_OOP_2017Class->objectName().isEmpty())
            Examen_OOP_2017Class->setObjectName(QStringLiteral("Examen_OOP_2017Class"));
        Examen_OOP_2017Class->resize(600, 400);
        menuBar = new QMenuBar(Examen_OOP_2017Class);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        Examen_OOP_2017Class->setMenuBar(menuBar);
        mainToolBar = new QToolBar(Examen_OOP_2017Class);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        Examen_OOP_2017Class->addToolBar(mainToolBar);
        centralWidget = new QWidget(Examen_OOP_2017Class);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        Examen_OOP_2017Class->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(Examen_OOP_2017Class);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        Examen_OOP_2017Class->setStatusBar(statusBar);

        retranslateUi(Examen_OOP_2017Class);

        QMetaObject::connectSlotsByName(Examen_OOP_2017Class);
    } // setupUi

    void retranslateUi(QMainWindow *Examen_OOP_2017Class)
    {
        Examen_OOP_2017Class->setWindowTitle(QApplication::translate("Examen_OOP_2017Class", "Examen_OOP_2017", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class Examen_OOP_2017Class: public Ui_Examen_OOP_2017Class {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_EXAMEN_OOP_2017_H
